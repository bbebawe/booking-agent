package me.bbebawe.booking_agent.service;

import me.bbebawe.booking_agent.entity.Booking;
import me.bbebawe.booking_agent.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findBooking(String sqlQuery) {
        return bookingRepository.queryBooking(sqlQuery);
    }

    public Booking createBooking(Booking bookingDetails) {
        return bookingRepository.createBooking(bookingDetails);
    }
}
