package com.polstat.perpustakaan.repository;

import com.polstat.perpustakaan.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    List<BookLoan> findByMemberId(Long memberId);
    List<BookLoan> findByBookId(Long bookId);

    // Tambahkan query untuk mengecek apakah ada buku yang sedang dipinjam
    boolean existsByBookIdAndStatus(Long bookId, String status);
}
