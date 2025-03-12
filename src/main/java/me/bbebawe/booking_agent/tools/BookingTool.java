package me.bbebawe.booking_agent.tools;

import lombok.extern.slf4j.Slf4j;
import me.bbebawe.booking_agent.entity.Booking;
import me.bbebawe.booking_agent.service.BookingService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BookingTool {

    @Autowired
    private BookingService bookingService;

    @Tool(description = "Get Booking Details from Database using SQL statement from user input")
    public List<Booking> getBookingDetails(String sqlQuery) {
        log.info("Getting Booking:{}", sqlQuery);
        return bookingService.findBooking(sqlQuery);
    }

    @Tool(description = "Create Booking Details in Database")
    Booking createBooking(Booking bookingDetails) {
        log.info("Creating Booking:{}", bookingDetails.toString());
        return bookingService.createBooking(bookingDetails);
    }

}
