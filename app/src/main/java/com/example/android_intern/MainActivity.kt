package com.example.android_intern


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val gson = Gson()
    private val phoneBook = gson.fromJson(PhoneBookJson, Array<PhoneBook>::class.java).asList()
    private val adapter = PhoneAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        setEditListener()
    }

    private fun setEditListener() {
        binding.textFilter.addTextChangedListener {
            adapter.setList(phoneBook.filter { "${it.name} ${it.phone} ${it.type}".contains(binding.textFilter.text) })
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewPhone.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPhone.adapter = adapter
        adapter.setList(phoneBook)
    }
}