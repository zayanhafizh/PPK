package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BookLoanDto;

import java.util.List;

public interface BookLoanService {
    BookLoanDto createLoan(BookLoanDto loanDto);
    BookLoanDto returnBook(Long loanId);
    List<BookLoanDto> getLoansByMemberId(Long memberId);
    List<BookLoanDto> getLoansByBookId(Long bookId);
}
