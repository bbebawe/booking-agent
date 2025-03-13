package me.bbebawe.booking_agent.service;

import lombok.RequiredArgsConstructor;
import me.bbebawe.booking_agent.entity.Booking;
import me.bbebawe.booking_agent.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public List<Booking> findBooking(String sqlQuery) {
        return bookingRepository.queryBooking(sqlQuery);
    }

    public Booking createBooking(Booking bookingDetails) {
        return bookingRepository.createBooking(bookingDetails);
    }
}
