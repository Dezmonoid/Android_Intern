package com.example.android_intern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.ActivityFullImageBinding

private const val LOAD_NAME = "URL"

class FullImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide
            .with(this)
            .load(intent.getStringExtra(LOAD_NAME))
            .into(binding.fullImageView)
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}