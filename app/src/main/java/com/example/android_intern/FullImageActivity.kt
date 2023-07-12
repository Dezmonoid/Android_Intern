package com.example.android_intern

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class FullImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)
        Glide
            .with(this)
            .load(intent.getStringExtra("URL"))
            .into(findViewById(R.id.IV2))
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener { finish() }
    }
}