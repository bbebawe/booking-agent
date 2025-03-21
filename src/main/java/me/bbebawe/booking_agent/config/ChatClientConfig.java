package me.bbebawe.booking_agent.config;

import io.modelcontextprotocol.client.McpSyncClient;
import me.bbebawe.booking_agent.tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class ChatClientConfig {

    @Value("classpath:/prompts/system-message.st")
    private Resource systemPromptResource;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder,
                                 List<McpSyncClient> mcpClients,
                                 WeatherTool weatherTool,
                                 VectorStore vectorStore) {

        List<ToolCallback> toolCallbacks = new ArrayList<>();

        // add MCP Servers
        toolCallbacks.addAll(McpToolUtils.getToolCallbacksFromSyncClients(mcpClients));
        // add Spring AI Tools
        toolCallbacks.addAll(List.of(ToolCallbacks.from(weatherTool)));

        return builder
                .defaultSystem(systemPromptResource)
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        new QuestionAnswerAdvisor(vectorStore, SearchRequest.builder().similarityThreshold(0.8d).topK(5).build()), // RAG
                        new MessageChatMemoryAdvisor(new InMemoryChatMemory()) // CHAT MEMORY
                )
                .defaultTools(toolCallbacks)
                .build();
    }

}
