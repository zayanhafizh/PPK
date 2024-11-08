package com.polstat.roombooking.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String bookedBy;
}
