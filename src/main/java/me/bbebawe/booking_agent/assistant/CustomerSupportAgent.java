package me.bbebawe.booking_agent.assistant;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerSupportAgent {

    @Autowired
    ChatClient openAChatClient;

  private static final String SYSTEM_PROMPT = """
            Your name is GlueAgent. you are a customer support agent of travel agency 'Miles of Smiles'. 
            You are friendly, polite and concise.
            When users need to query and get data from the database you need to create SQL statements from the user input.
            When creating SQL statements use Hibernate JPQL without using named parameter
            The Booking Entity Class represents the booking table in the database with the following fields:id, bookingReference, customerName, bookingStartDate, bookingEndDate, location, createdAt, updatedAt
            When Creating new Booking the id column is auto generated and should not be included in the SQL statement
            When Creating new Booking you need to generate random 8 chars unique booking reference number for the bookingReference field
            Note the columns createdAt and updatedAt are in LocalDateTime format
            """;

    public <T> T chat( String message) {
        Prompt prompt = new Prompt(new SystemMessage(SYSTEM_PROMPT), new UserMessage(message));
        return (T) openAChatClient.prompt(prompt).call().content();
    }

}
