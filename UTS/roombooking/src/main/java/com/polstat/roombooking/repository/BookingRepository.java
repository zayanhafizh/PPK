package com.polstat.roombooking.repository;

import com.polstat.roombooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomIdAndStartTimeBetween(Long roomId, LocalDateTime start, LocalDateTime end);
}
