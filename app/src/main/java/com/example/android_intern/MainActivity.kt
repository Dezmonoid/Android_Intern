package com.example.android_intern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android_intern.databinding.ActivityMainBinding
import com.example.android_intern.fragment.CharacterFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var firstLaunch = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (firstLaunch) {
            changeFragment(CharacterFragment())
            firstLaunch = false
        }

    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}