package com.example.android_intern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_intern.databinding.ActivityMainBinding
import timber.log.Timber

private const val TAG = "Intern"
lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timberLogView()
    }

    private fun timberLogView() {
        binding.buttonOk.setOnClickListener {
            Timber.tag(TAG).i(binding.editText.text.toString())
        }
    }
}