package com.polstat.helloworld

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set up edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the TextView and Button
        val txtView1: TextView = findViewById(R.id.txtView1)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)

        // Set click listener for the button1
        button1.setOnClickListener {
            // Change text of TextView when button is clicked
            txtView1.text = "Button 1 Clicked!"
        }

        // Set click listener for the button2
        button2.setOnClickListener {
            // Change text of TextView when button is clicked
            txtView1.text = "Button 2 Clicked!"
        }
    }
}
