package com.example.android_intern

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonUse: Button = findViewById(R.id.button_listen)
        val textView: TextView = findViewById(R.id.text_view)
        buttonUse.setOnClickListener {
            textView.text = "Нажата кнопка"
        }
    }
}