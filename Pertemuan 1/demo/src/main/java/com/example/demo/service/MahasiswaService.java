package com.example.demo.service;

import com.example.demo.model.Mahasiswa;
import com.example.demo.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MahasiswaService {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    // Get all Mahasiswa records
    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaRepository.findAll();
    }

    // Save a new Mahasiswa
    public Mahasiswa saveMahasiswa(Mahasiswa mahasiswa) {
        return mahasiswaRepository.save(mahasiswa);
    }

    // Get Mahasiswa by ID
    public Mahasiswa getMahasiswaById(Long id) {
        Optional<Mahasiswa> mahasiswa = mahasiswaRepository.findById(id);
        return mahasiswa.orElse(null); // Returns null if Mahasiswa not found
    }

    // Update an existing Mahasiswa
    public Mahasiswa updateMahasiswa(Long id, Mahasiswa updatedMahasiswa) {
        Optional<Mahasiswa> existingMahasiswa = mahasiswaRepository.findById(id);

        if (existingMahasiswa.isPresent()) {
            Mahasiswa mahasiswa = existingMahasiswa.get();
            mahasiswa.setNama(updatedMahasiswa.getNama());
            mahasiswa.setNim(updatedMahasiswa.getNim());
            mahasiswa.setJurusan(updatedMahasiswa.getJurusan());
            mahasiswa.setTanggalLahir(updatedMahasiswa.getTanggalLahir());

            return mahasiswaRepository.save(mahasiswa); // Update and save
        }

        return null; // Or throw an exception if preferred
    }

    public Mahasiswa deleteMahasiswa(Long id) {
        mahasiswaRepository.deleteById(id);
        return null;
    }

    public List<Mahasiswa> searchMahasiswa(String keyword) {
        return mahasiswaRepository.search(keyword);
    }
}
