package com.example.android_intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.ActivityFullScreenBinding

private const val IMAGE_URL = "https://bigpicture.ru/wp-content/uploads/2012/04/2184.jpg"

class FullScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide
            .with(this)
            .load(IMAGE_URL)
            .into(binding.image)
    }
}