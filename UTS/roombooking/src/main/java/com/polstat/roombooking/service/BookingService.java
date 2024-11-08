package com.polstat.roombooking.service;

import com.polstat.roombooking.dto.BookingDTO;
import com.polstat.roombooking.exception.RoomNotAvailableException;
import com.polstat.roombooking.entity.Booking;
import com.polstat.roombooking.entity.Room;
import com.polstat.roombooking.repository.BookingRepository;
import com.polstat.roombooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(BookingDTO bookingDTO) {
        Room room = roomRepository.findById(bookingDTO.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        // Validasi ketersediaan ruangan
        List<Booking> overlappingBookings = bookingRepository
                .findByRoomIdAndStartTimeBetween(bookingDTO.getRoomId(),
                        bookingDTO.getStartTime(),
                        bookingDTO.getEndTime());
        if (!overlappingBookings.isEmpty()) {
            throw new RoomNotAvailableException("Room is not available for the selected time period");
        }

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setBookedBy(bookingDTO.getBookedBy());

        return bookingRepository.save(booking);
    }
}
