package com.polstat.perpustakaan.mapper;

import com.polstat.perpustakaan.dto.BookLoanDto;
import com.polstat.perpustakaan.entity.BookLoan;

public class BookLoanMapper {
    public static BookLoanDto mapToBookLoanDto(BookLoan loan) {
        return BookLoanDto.builder()
                .id(loan.getId())
                .memberId(loan.getMember().getId())
                .bookId(loan.getBook().getId())
                .loanDate(loan.getLoanDate())
                .returnDate(loan.getReturnDate())
                .status(loan.getStatus())
                .lateDays(loan.getLateDays())
                .build();
    }
}
