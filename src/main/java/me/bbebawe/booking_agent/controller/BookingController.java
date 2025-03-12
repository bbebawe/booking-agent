package me.bbebawe.booking_agent.controller;

import lombok.RequiredArgsConstructor;
import me.bbebawe.booking_agent.assistant.CustomerSupportAgent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final CustomerSupportAgent customerSupportAgent;

    @GetMapping("/chat")
    public ResponseEntity<String> generateChatResponse(@RequestParam String message) {
        return ResponseEntity.ok(customerSupportAgent.chat(message));
    }

}
