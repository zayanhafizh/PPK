package com.polstat.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        // Ambil data yang dikirim dari MainActivity
        val name = intent.getStringExtra("name")
        val surname = intent.getStringExtra("surname")
        val age = intent.getStringExtra("age")

        // Menampilkan data pada TextView
        val nameTextView = findViewById<TextView>(R.id.textViewName)
        nameTextView.text = "$name"
        val surnameTextView = findViewById<TextView>(R.id.textViewSurname)
        surnameTextView.text = "$surname"
        val ageTextView = findViewById<TextView>(R.id.textViewAge)
        ageTextView.text = "$age"

    }
}

