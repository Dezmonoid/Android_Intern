package com.example.android_intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_intern.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            binding.tv.text = binding.root.context.getString(R.string.text_message)
        }
    }
}