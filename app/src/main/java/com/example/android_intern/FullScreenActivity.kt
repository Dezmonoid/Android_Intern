package com.example.android_intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.ActivityFullScreenBinding

private lateinit var binding: ActivityFullScreenBinding

private const val ImageURL = "https://bigpicture.ru/wp-content/uploads/2012/04/2184.jpg"

class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Glide
            .with(this)
            .load(ImageURL)
            .into(binding.image)
    }
}