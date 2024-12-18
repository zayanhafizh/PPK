package com.polstat.myapplication

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mendapatkan referensi tombol
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
    }

    // Tombol pertama untuk melakukan dial telepon
    fun btnClick(view: View) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:081234567899"))
        startActivity(intent)
    }

    // Tombol kedua untuk membuka pengaturan
    fun btn2Click(view: View) {
        val intent = Intent(Settings.ACTION_SETTINGS)
        startActivity(intent)
        Toast.makeText(this, "You have pressed: ${btn2.text}", Toast.LENGTH_LONG).show()
    }

    // Tombol ketiga (Contoh aksi lain)
    fun btn3Click(view: View) {
//        val searchIntent = Intent(Intent.ACTION_WEB_SEARCH)
//        searchIntent.putExtra(SearchManager.QUERY, "intent android")
//        startActivity(searchIntent)
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "audio/mp3"  // Hanya memilih file audio dengan tipe MP3
        startActivity(intent)
    }
}
