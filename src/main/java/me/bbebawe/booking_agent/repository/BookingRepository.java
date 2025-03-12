package me.bbebawe.booking_agent.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.bbebawe.booking_agent.entity.Booking;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class BookingRepository {


    @PersistenceContext
    private EntityManager entityManager;

    public List<Booking> queryBooking(String sqlQuery) {
        List<Booking> result = entityManager.createQuery(sqlQuery, Booking.class).getResultList();

        if (!result.isEmpty()) {
            return result;
        }
        return Collections.emptyList();
    }


    public Booking createBooking(Booking bookingDetails) {
        entityManager.persist(bookingDetails);
        return bookingDetails;
    }
}
