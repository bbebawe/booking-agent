package me.bbebawe.booking_agent.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class WeatherTool {

    @Autowired
    private RestClient weatherClient;

    @Tool(description = "Get Weather details for a given city")
    public String getWeather(String city) {
        log.info("Getting Weather: {}", city);
        return weatherClient.get()
                .uri("/current.json?q=" + city)
                .retrieve()
                .body(String.class);
    }

}
