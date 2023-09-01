package com.example.android_intern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.ActivityFullImageBinding



class FullImageActivity : AppCompatActivity() {
    companion object {
        const val ARG_URL = "URL"
    }
    private lateinit var binding: ActivityFullImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide
            .with(this)
            .load(intent.getStringExtra(ARG_URL))
            .into(binding.fullImageView)
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}