package com.polstat.perpustakaan.controller;

import com.polstat.perpustakaan.dto.BookLoanDto;
import com.polstat.perpustakaan.service.BookLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class BookLoanController {

    @Autowired
    private BookLoanService bookLoanService;

    @PostMapping("/borrow")
    public ResponseEntity<BookLoanDto> borrowBook(@RequestBody BookLoanDto loanDto) {
        return ResponseEntity.ok(bookLoanService.createLoan(loanDto));
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<BookLoanDto> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookLoanService.returnBook(id));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BookLoanDto>> getLoansByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(bookLoanService.getLoansByMemberId(memberId));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BookLoanDto>> getLoansByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookLoanService.getLoansByBookId(bookId));
    }
}
