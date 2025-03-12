package me.bbebawe.booking_agent.config;

import me.bbebawe.booking_agent.service.BookingService;
import me.bbebawe.booking_agent.tools.BookingTool;
import me.bbebawe.booking_agent.tools.DateTimeTool;
import me.bbebawe.booking_agent.tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIClientConfig {


    @Bean
    public ChatClient openAiChatClient(ChatModel chatModel, BookingTool bookingTool, DateTimeTool dateTimeTool, WeatherTool weatherTool, BookingService bookingService) { // bookingService to avoid NPL
        return ChatClient.builder(chatModel)
                .defaultTools(bookingTool, dateTimeTool, weatherTool)
                .build();
    }

}
