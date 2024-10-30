package com.polstat.perpustakaan.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookLoanDto {
    private Long id;
    private Long memberId;
    private Long bookId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String status;
    private Integer lateDays;
}
