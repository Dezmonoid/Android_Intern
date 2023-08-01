package com.example.android_intern

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intern.databinding.ActivityFullScreenBinding
import com.example.android_intern.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFullScreenActivity()
    }

    fun openFullScreenActivity() {
        val intent = Intent(this, FullScreenActivity::class.java)
        binding.buttonFullscreen.setOnClickListener {
            startActivity(intent)
        }

    }
}
