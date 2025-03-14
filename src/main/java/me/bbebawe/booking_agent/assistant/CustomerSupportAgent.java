package me.bbebawe.booking_agent.assistant;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerSupportAgent {

    private final ChatClient chatClient;

    public String chat(String message) {
        return chatClient.prompt()
                .system(sp -> sp.param("agentName", "GlueAgent"))
                .user(message)
                .call().content();
    }

}
