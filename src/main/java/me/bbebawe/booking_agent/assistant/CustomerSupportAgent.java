package me.bbebawe.booking_agent.assistant;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerSupportAgent {

    private final ChatClient chatClient;
    private static final String AGENT_NAME = "agentName";
    private static final String AGENT_NAME_VALUE = "BookingAgent";

    public String chat(String message) {
        return chatClient.prompt()
                .system(sp -> sp.param(AGENT_NAME, AGENT_NAME_VALUE))
                .user(message)
                .call().content();
    }
}
