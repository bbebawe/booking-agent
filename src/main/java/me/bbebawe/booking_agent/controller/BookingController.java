package me.bbebawe.booking_agent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.bbebawe.booking_agent.assistant.CustomerSupportAgent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    private final CustomerSupportAgent customerSupportAgent;

    @PostMapping("/chat")
    public ResponseEntity<String> generateChatResponse(@RequestBody String message) {
        return ResponseEntity.ok(customerSupportAgent.chat(message));
    }

}
