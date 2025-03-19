package me.bbebawe.booking_agent.repository;

import jakarta.persistence.EntityManager;
import me.bbebawe.booking_agent.entity.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import jakarta.persistence.TypedQuery;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testQueryBooking_withResults() {
        String sqlQuery = "SELECT b FROM Booking b";
        Booking booking = Booking.builder().bookingReference("BR123").customerName("John Doe").bookingStartDate("2023-10-01").bookingEndDate("2023-10-05").location("New York").build();
        List<Booking> expectedBookings = List.of(booking);

        TypedQuery<Booking> queryMock = mock(TypedQuery.class);
        when(entityManager.createQuery(sqlQuery, Booking.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(expectedBookings);

        List<Booking> actualBookings = bookingRepository.queryBooking(sqlQuery);

        assertEquals(expectedBookings, actualBookings);
        verify(entityManager).createQuery(sqlQuery, Booking.class);
    }

    @Test
    void testQueryBooking_noResults() {
        String sqlQuery = "SELECT b FROM Booking b";

        TypedQuery<Booking> queryMock = mock(TypedQuery.class);
        when(entityManager.createQuery(sqlQuery, Booking.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(Collections.emptyList());

        List<Booking> actualBookings = bookingRepository.queryBooking(sqlQuery);

        assertEquals(Collections.emptyList(), actualBookings);
        verify(entityManager).createQuery(sqlQuery, Booking.class);
    }

    @Test
    void testCreateBooking() {
        Booking bookingDetails = Booking.builder().bookingReference("BR123").customerName("Jane Doe").bookingStartDate("2023-10-10").bookingEndDate("2023-10-15").location("Los Angeles").build();

        bookingRepository.createBooking(bookingDetails);

        verify(entityManager).persist(bookingDetails);
    }
}
