package me.bbebawe.booking_agent.service;

import me.bbebawe.booking_agent.entity.Booking;
import me.bbebawe.booking_agent.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findBooking(String sqlQuery) {
        if (Objects.isNull(sqlQuery) || sqlQuery.trim().isEmpty()) {
            throw new IllegalArgumentException("SQL query must not be null or empty");
        }
        return bookingRepository.queryBooking(sqlQuery);
    }

    public Booking createBooking(Booking bookingDetails) {
        if (Objects.isNull(bookingDetails)) {
            throw new IllegalArgumentException("Booking details must not be null");
        }
        return bookingRepository.createBooking(bookingDetails);
    }
}
