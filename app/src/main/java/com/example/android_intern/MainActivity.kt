package com.example.android_intern


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.android_intern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_phone, PhoneFragment.newInstance())
            .commit()
        setListeners()
    }

    private fun setListeners() {
        binding.etInputFilter.addTextChangedListener {
            val filteredList =
                getInitNumbers()
                    .filter { "${it.name} ${it.phone} ${it.type}".contains(binding.etInputFilter.text) }
            PhoneFragment.phoneAdapter.submitList(filteredList)
        }
    }

}