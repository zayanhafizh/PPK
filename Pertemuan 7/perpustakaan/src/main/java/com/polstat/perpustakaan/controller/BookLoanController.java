package com.polstat.perpustakaan.controller;

import com.polstat.perpustakaan.dto.BookLoanDto;
import com.polstat.perpustakaan.service.BookLoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class BookLoanController {

    @Autowired
    private BookLoanService bookLoanService;

    @Operation(summary = "Borrow a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book borrowed successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookLoanDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book or member not found", content = @Content)
    })
    @PostMapping("/borrow")
    public ResponseEntity<BookLoanDto> borrowBook(@RequestBody BookLoanDto loanDto) {
        return ResponseEntity.ok(bookLoanService.createLoan(loanDto));
    }

    @Operation(summary = "Return a borrowed book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book returned successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookLoanDto.class))),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)
    })
    @PutMapping("/return/{id}")
    public ResponseEntity<BookLoanDto> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookLoanService.returnBook(id));
    }

    @Operation(summary = "Get loans by member ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of loans for the member",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookLoanDto.class))),
            @ApiResponse(responseCode = "404", description = "Member not found", content = @Content)
    })
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BookLoanDto>> getLoansByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(bookLoanService.getLoansByMemberId(memberId));
    }

    @Operation(summary = "Get loans by book ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of loans for the book",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookLoanDto.class))),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<BookLoanDto>> getLoansByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookLoanService.getLoansByBookId(bookId));
    }
}
