package com.example.android_intern


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.Gson


private const val TAG = "Tag"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val gson = Gson()
    private val phoneAdapter = PhoneAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        phoneAdapter.submitList(getInitNumbers())
        setListeners()
    }

    private fun setListeners() {
        binding.etInputFilter.addTextChangedListener {
            val filteredList =
                getInitNumbers().filter { "${it.name} ${it.phone} ${it.type}".contains(binding.etInputFilter.text) }
            phoneAdapter.submitList(filteredList)
        }
    }

    private fun getInitNumbers() =
        gson.fromJson(phoneBookJson, Array<PhoneBook>::class.java).asList()

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = phoneAdapter
    }

}