package com.example.android_intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_intern.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { binding.tv.text = "Добрейший вечерочек" }
    }
}