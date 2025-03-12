package me.bbebawe.booking_agent.tools;


import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class DateTimeTool {


    @Tool(name = "Get_Current_DateTime", value = "Get Current Date and Time")
    public LocalDateTime getCurrentDateTime() {
        log.info("Getting Current Date and Time");
        return LocalDateTime.now();
    }
}
