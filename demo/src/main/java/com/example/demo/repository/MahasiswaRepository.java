package com.example.demo.repository;

import com.example.demo.model.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
    boolean existsByNim(String nim);  // Method untuk cek apakah NIM sudah ada di database
}
