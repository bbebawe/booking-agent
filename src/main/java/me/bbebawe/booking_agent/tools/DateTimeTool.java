package me.bbebawe.booking_agent.tools;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class DateTimeTool {

    @Tool(description = "Get Current Date and Time")
    public LocalDateTime getCurrentDateTime() {
        log.info("Getting Current Date and Time");
        return LocalDateTime.now();
    }
}
