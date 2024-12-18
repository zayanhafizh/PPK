package com.polstat.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class KalkulatorPersegiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi elemen UI
        val inputSisi: EditText = findViewById(R.id.inputSisi)
        val buttonHitung: Button = findViewById(R.id.buttonHitung)
        val resultText: TextView = findViewById(R.id.resultText)

        // Menangani klik tombol hitung
        buttonHitung.setOnClickListener {
            // Ambil input panjang sisi
            val sisi = inputSisi.text.toString()

            if (sisi.isNotEmpty()) {
                try {
                    // Menghitung luas persegi
                    val sisiLength = sisi.toDouble() // mencoba mengkonversi input ke Double
                    val luas = sisiLength * sisiLength

                    // Menampilkan hasil
                    resultText.text = "Luas Persegi: $luas"
                } catch (e: NumberFormatException) {
                    // Menangani kesalahan input (jika bukan angka)
                    Toast.makeText(this, "Mohon masukkan angka yang valid!", Toast.LENGTH_SHORT).show()
                    resultText.text = "" // Menyembunyikan hasil sebelumnya
                }
            } else {
                // Menangani kasus ketika input kosong
                Toast.makeText(this, "Mohon masukkan panjang sisi!", Toast.LENGTH_SHORT).show()
                resultText.text = "" // Menyembunyikan hasil sebelumnya
            }
        }
    }
}
