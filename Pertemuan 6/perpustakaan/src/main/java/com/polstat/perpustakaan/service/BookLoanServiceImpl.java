package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BookLoanDto;
import com.polstat.perpustakaan.entity.Book;
import com.polstat.perpustakaan.entity.BookLoan;
import com.polstat.perpustakaan.entity.Member;
import com.polstat.perpustakaan.exception.BookAlreadyLoanedException;
import com.polstat.perpustakaan.mapper.BookLoanMapper;
import com.polstat.perpustakaan.repository.BookLoanRepository;
import com.polstat.perpustakaan.repository.BookRepository;
import com.polstat.perpustakaan.repository.MemberRepository;
import com.polstat.perpustakaan.service.BookLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookLoanServiceImpl implements BookLoanService {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookLoanDto createLoan(BookLoanDto loanDto) {
        // Cek apakah buku sudah dipinjam
        boolean isBookOnLoan = bookLoanRepository.existsByBookIdAndStatus(loanDto.getBookId(), "sedang dipinjam");

        if (isBookOnLoan) {
            throw new BookAlreadyLoanedException("Buku ini sedang dipinjam oleh member lain.");
        }

        // Jika buku belum dipinjam, lanjutkan proses peminjaman
        Member member = memberRepository.findById(loanDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member tidak ditemukan"));
        Book book = bookRepository.findById(loanDto.getBookId())
                .orElseThrow(() -> new RuntimeException("Buku tidak ditemukan"));

        BookLoan bookLoan = BookLoan.builder()
                .member(member)
                .book(book)
                .loanDate(LocalDate.now())
                .status("sedang dipinjam")
                .build();

        bookLoan = bookLoanRepository.save(bookLoan);
        return BookLoanMapper.mapToBookLoanDto(bookLoan);
    }


    @Override
    public BookLoanDto returnBook(Long loanId) {
        BookLoan loan = bookLoanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan tidak ditemukan"));

        loan.setReturnDate(LocalDate.now());
        loan.setStatus("sudah dikembalikan");

        // Hitung hari keterlambatan
        long lateDays = ChronoUnit.DAYS.between(loan.getLoanDate().plusDays(14), loan.getReturnDate());
        loan.setLateDays((int) Math.max(0, lateDays));

        bookLoanRepository.save(loan);
        return BookLoanMapper.mapToBookLoanDto(loan);
    }

    @Override
    public List<BookLoanDto> getLoansByMemberId(Long memberId) {
        return bookLoanRepository.findByMemberId(memberId)
                .stream()
                .map(BookLoanMapper::mapToBookLoanDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookLoanDto> getLoansByBookId(Long bookId) {
        return bookLoanRepository.findByBookId(bookId)
                .stream()
                .map(BookLoanMapper::mapToBookLoanDto)
                .collect(Collectors.toList());
    }
}
