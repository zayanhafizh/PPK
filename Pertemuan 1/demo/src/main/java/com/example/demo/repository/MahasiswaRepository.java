package com.example.demo.repository;

import com.example.demo.model.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
    boolean existsByNim(String nim);  // Method untuk cek apakah NIM sudah ada di database

    @Query("SELECT m FROM Mahasiswa m WHERE m.nim LIKE %:keyword% OR LOWER(m.nama) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Mahasiswa> search(String keyword);
}
