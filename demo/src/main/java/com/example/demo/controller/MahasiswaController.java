package com.example.demo.controller;

import com.example.demo.model.Mahasiswa;
import com.example.demo.service.MahasiswaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MahasiswaController {

    @Autowired
    private MahasiswaService mahasiswaService;

    @GetMapping("/mahasiswa")
    public String getAllMahasiswa(Model model) {
        List<Mahasiswa> mahasiswaList = mahasiswaService.getAllMahasiswa();
        model.addAttribute("mahasiswaList", mahasiswaList);
        return "mahasiswa-list";  // nama file HTML yang akan dirender
    }

    @GetMapping("/tambah")
    public String addMahasiswa(Model model) {
        model.addAttribute("mahasiswa", new Mahasiswa());
        return "addMahasiswa";
    }

    @PostMapping("/tambah")
    public String addMahasiswa(@ModelAttribute @Valid Mahasiswa mahasiswa, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mahasiswa", mahasiswa);
            return "addMahasiswa";  // kembali ke halaman input jika ada error
        }
        mahasiswaService.saveMahasiswa(mahasiswa);
        return "redirect:/mahasiswa";
    }

    @GetMapping("/edit/{id}")
    public String editMahasiswa(@PathVariable Long id, Model model) {
        Mahasiswa mahasiswa = mahasiswaService.getMahasiswaById(id);
        if (mahasiswa != null) {
            model.addAttribute("mahasiswa", mahasiswa);
            return "editMahasiswa";
        }
        return "redirect:/mahasiswa"; // Redirect jika mahasiswa tidak ditemukan
    }

    @PostMapping("/edit/{id}")
    public String updateMahasiswa(@PathVariable Long id, @ModelAttribute @Valid Mahasiswa mahasiswa, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mahasiswa", mahasiswa);
            return "editMahasiswa";
        }
        mahasiswaService.updateMahasiswa(id, mahasiswa);
        return "redirect:/mahasiswa";
    }

    @GetMapping("/hapus/{id}")
    public String deleteMahasiswa(@PathVariable Long id, Model model) {
        Mahasiswa mahasiswa = mahasiswaService.deleteMahasiswa(id);
        if (mahasiswa != null) {
            model.addAttribute("mahasiswa", mahasiswa);
            return "redirect:/mahasiswa";
        }
        return "redirect:/mahasiswa";
    }
}
