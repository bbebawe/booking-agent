package me.bbebawe.booking_agent.tools;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class WeatherTool {

    private final RestClient weatherClient;
    private static final String CURRENT_WEATHER_URI = "/current.json?q=";

    @Tool(description = "Get Weather details for a given city")
    public String getWeather(String city) {
        log.info("Getting Weather for: {}", city);
        return weatherClient.get()
                .uri(CURRENT_WEATHER_URI + city)
                .retrieve()
                .body(String.class);
    }

}
