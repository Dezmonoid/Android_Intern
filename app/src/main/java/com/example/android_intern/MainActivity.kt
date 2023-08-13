package com.example.android_intern

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenerNewActivity()
    }

    private fun listenerNewActivity() {
        val intent = Intent(this, FullScreenActivity::class.java)
        binding.buttonFullscreen.setOnClickListener {
            startActivity(intent)
        }

    }
}
