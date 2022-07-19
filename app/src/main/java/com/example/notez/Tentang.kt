package com.example.notez

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.tentang.*

class Tentang : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tentang)

        buttonBack2.setOnClickListener() {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}