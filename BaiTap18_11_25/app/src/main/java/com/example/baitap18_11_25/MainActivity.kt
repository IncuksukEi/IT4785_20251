package com.example.baitap18_11_25

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnBai1 = findViewById<Button>(R.id.btnBai1)
        val btnBai2 = findViewById<Button>(R.id.btnBai2)
        val btnBai3 = findViewById<Button>(R.id.btnBai3)

        btnBai1.setOnClickListener {
            startActivity(Intent(this, Bai1Activity::class.java))
        }

        btnBai2.setOnClickListener {
            startActivity(Intent(this, Bai2Activity::class.java))
        }

        btnBai3.setOnClickListener {
            startActivity(Intent(this, Bai3Activity::class.java))
        }
    }
}
