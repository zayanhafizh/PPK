package com.polstat.perpustakaan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book_loans")
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDate loanDate;

    @Column(nullable = true)
    private LocalDate returnDate;

    @Column(nullable = false)
    private String status; // "sedang dipinjam" atau "sudah dikembalikan"

    @Column(nullable = true)
    private Integer lateDays; // Jumlah hari keterlambatan pengembalian, jika ada
}
