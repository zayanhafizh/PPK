package com.polstat.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.editTextName)
        val surnameEditText = findViewById<EditText>(R.id.editTextSurname)
        val ageEditText = findViewById<EditText>(R.id.editTextAge)
        val submitButton = findViewById<Button>(R.id.btnSubmit)

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val surname = surnameEditText.text.toString()
            val age = ageEditText.text.toString()

            if (name.isNotEmpty() && surname.isNotEmpty() && age.isNotEmpty()) {
                // Kirim data ke DisplayActivity menggunakan Intent
                val intent = Intent(this, DisplayActivity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("surname", surname)
                intent.putExtra("age", age)
                startActivity(intent)
            } else {
                // Menampilkan Toast jika ada field yang kosong
                Toast.makeText(this, "Harap lengkapi semua data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

