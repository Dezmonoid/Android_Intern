package com.example.android_intern


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val gson = Gson()
    private val phoneBook = gson.fromJson(PhoneBookJson, Array<PhoneBook>::class.java).asList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        fillRecyclerViewFilter()
        filterListener()
    }

    private fun filterListener() {
        binding.textFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                fillRecyclerViewFilter()
            }
        })
    }

    private fun fillRecyclerViewFilter() {
        binding.recyclerViewPhone.adapter = PhoneAdapter(
            phoneBook.filter { "${it.name} ${it.phone} ${it.type}".contains(binding.textFilter.text) })
    }

    private fun initRecyclerView() {
        binding.recyclerViewPhone.hasFixedSize()
        binding.recyclerViewPhone.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPhone.adapter = PhoneAdapter(phoneBook)
    }
}