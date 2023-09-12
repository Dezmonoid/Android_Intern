package com.example.android_intern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val gson = Gson()
    private val phoneBook = gson.fromJson(phoneBookJson, Array<PhoneBook>::class.java).asList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        setListener()
    }

    private fun setListener() {
        binding.buttonFilter.setOnClickListener {
            val filterPhoneBook =
                phoneBook.filter { "${it.name} ${it.phone} ${it.type}".contains(binding.textFilter.text) }
            binding.recyclerViewPhone.adapter = PhoneAdapter(filterPhoneBook)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewPhone.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPhone.adapter = PhoneAdapter(phoneBook)
    }
}