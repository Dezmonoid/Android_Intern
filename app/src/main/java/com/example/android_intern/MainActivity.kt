package com.example.android_intern

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val buttonOk: Button = findViewById(R.id.button_ok)
        buttonOk.setOnClickListener {
            Toast.makeText(
                this,
                "Нажата ОК",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}