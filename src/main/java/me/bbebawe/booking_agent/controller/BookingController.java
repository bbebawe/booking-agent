package me.bbebawe.booking_agent.controller;

import me.bbebawe.booking_agent.assistant.CustomerSupportAgent;
import me.bbebawe.booking_agent.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private CustomerSupportAgent customerSupportAgent;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/chat")
    public ResponseEntity<String> generateChatResponse(@RequestParam String message) {
        return ResponseEntity.ok(customerSupportAgent.chat(message));
    }

}
