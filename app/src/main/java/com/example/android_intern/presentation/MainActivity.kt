package com.example.android_intern.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intern.R
import com.example.android_intern.databinding.ActivityMainBinding
import com.example.android_intern.presentation.forecast.ForecastFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            initFragment()
        }
    }

    private fun initFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.weatherFragment, ForecastFragment())
            .commit()
    }
}