package me.bbebawe.booking_agent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WeatherClientConfig {

    @Value("${clients.weather.base-url}")
    private String baseUrl;

    @Value("${clients.weather.api-key}")
    private String apiKey;

    @Bean
    public RestClient weatherClient() {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("key", apiKey)
                .build();
    }
}
