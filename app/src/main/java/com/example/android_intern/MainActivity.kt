package com.example.android_intern


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.Gson


private const val SETTING = "filterSetting"
private const val TEXT = "filterText"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var sharedPreference: SharedPreferences? = null
    private val gson = Gson()
    private val phoneAdapter = PhoneAdapter()
    private var loadFilter = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = getSharedPreferences(SETTING, Context.MODE_PRIVATE)
        initRecyclerView()
        loadFilter = sharedPreference?.getString(TEXT, "").toString()
        firstFilling(loadFilter)
        setListeners()
    }

    private fun firstFilling(loadFilter: String) {
        if (loadFilter.isEmpty()) {
            phoneAdapter.submitList(getInitNumbers())
        } else {
            phoneAdapter.submitList(getInitNumbers().filter {
                "${it.name} ${it.phone} ${it.type}".contains(
                    loadFilter
                )
            })
            binding.etInputFilter.setText(loadFilter)
        }

    }

    private fun setListeners() {
        binding.etInputFilter.addTextChangedListener {
            val filteredList =
                getInitNumbers().filter { "${it.name} ${it.phone} ${it.type}".contains(binding.etInputFilter.text) }
            phoneAdapter.submitList(filteredList)
            saveData()
        }
    }

    private fun saveData() {
        val sharedEditor = sharedPreference?.edit()
        sharedEditor?.putString(TEXT, binding.etInputFilter.text.toString())
        sharedEditor?.apply()
    }

    private fun getInitNumbers() =
        gson.fromJson(phoneBookJson, Array<PhoneBook>::class.java).asList()

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = phoneAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }
}