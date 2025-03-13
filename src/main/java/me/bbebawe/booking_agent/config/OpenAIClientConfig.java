package me.bbebawe.booking_agent.config;

import io.modelcontextprotocol.client.McpSyncClient;
import me.bbebawe.booking_agent.tools.BookingTool;
import me.bbebawe.booking_agent.tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class OpenAIClientConfig {

    @Bean
    public ChatClient openAiChatClient(ChatClient.Builder builder,
                                       WeatherTool weatherTool,
                                       BookingTool bookingTool,
                                       List<McpSyncClient> mcpClients) { // beans comes from components

        List<ToolCallback> toolCallbacks = new ArrayList<>();

        // add MCP tools
        toolCallbacks.addAll(McpToolUtils.getToolCallbacksFromSyncClients(mcpClients));
        // add Spring AI Tools
        toolCallbacks.addAll(List.of(ToolCallbacks.from(weatherTool, bookingTool)));

        return builder
                .defaultTools(toolCallbacks)
                .build();
    }


}
