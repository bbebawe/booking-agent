package me.bbebawe.booking_agent.config;

import io.modelcontextprotocol.client.McpSyncClient;
import me.bbebawe.booking_agent.tools.BookingTool;
import me.bbebawe.booking_agent.tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class OpenAIClientConfig {

    @Value("classpath:/prompts/system-message.st")
    private Resource systemPromptResource;

    @Bean
    public ChatClient openAiChatClient(ChatClient.Builder builder,
                                       List<McpSyncClient> mcpClients,
                                       WeatherTool weatherTool,
                                       BookingTool bookingTool) {

        List<ToolCallback> toolCallbacks = new ArrayList<>();

        // add MCP tools
        toolCallbacks.addAll(McpToolUtils.getToolCallbacksFromSyncClients(mcpClients));
        // add Spring AI Tools
        toolCallbacks.addAll(List.of(ToolCallbacks.from(weatherTool, bookingTool)));

        return builder
                .defaultSystem(systemPromptResource)
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(new InMemoryChatMemory()) // CHAT MEMORY
                )
                .defaultTools(toolCallbacks)
                .build();
    }

}
