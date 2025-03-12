package me.bbebawe.booking_agent.tools;

import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.bbebawe.booking_agent.entity.Booking;
import me.bbebawe.booking_agent.service.BookingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingTool {

    private final BookingService bookingService;
    
    @Tool(name = "Get_Booking_Details", value = "Get Booking Details from Database using SQL statement from user input")
    public List<Booking> getBookingDetails(String sqlQuery) {
        log.info("Getting Booking:{}", sqlQuery);
        return bookingService.findBooking(sqlQuery);
    }

    @Tool(name = "Create_Booking", value = "Create Booking Details in Database")
    Booking createBooking(Booking bookingDetails) {
        log.info("Creating Booking:{}", bookingDetails.toString());
        return bookingService.createBooking(bookingDetails);
    }

}
