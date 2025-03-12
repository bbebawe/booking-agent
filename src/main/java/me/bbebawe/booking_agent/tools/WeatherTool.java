package me.bbebawe.booking_agent.tools;

import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherTool {

    private final RestClient weatherClient;

    @Tool(name = "Get_City_Weather", value = "Get Weather details for a given city")
    public String getWeather(String city) {
        log.info("Getting Weather: {}", city);
        return weatherClient.get()
                .uri("/current.json?q=" + city)
                .retrieve()
                .body(String.class);
    }

}
