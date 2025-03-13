package me.bbebawe.booking_agent.assistant;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.*;

@Component
@RequiredArgsConstructor
public class CustomerSupportAgent {

    private final ChatClient openAChatClient;

    public String chat(String message) {
        return openAChatClient.prompt()
                .system(sp -> sp.param("agentName", "GlueAgent"))
                .user(message)
//                .advisors(a -> a
//                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, DEFAULT_CHAT_MEMORY_CONVERSATION_ID)
//                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .call().content();
    }

}
