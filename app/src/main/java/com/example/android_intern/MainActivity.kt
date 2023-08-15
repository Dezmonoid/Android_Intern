package com.example.android_intern


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.Gson


private const val PREFERENCES_NAME = "filterSetting"
private const val KEY_NAME = "filterText"
private const val EMPTY_STRING = ""

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var sharedPreference: SharedPreferences? = null
    private val gson = Gson()
    private val phoneAdapter = PhoneAdapter()
    private lateinit var loadFilter: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        initRecyclerView()
        firstFilling()
        setListeners()
    }

    private fun firstFilling() {
        loadFilter = sharedPreference?.getString(KEY_NAME, EMPTY_STRING).toString()
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
        sharedPreference
            ?.edit()
            ?.putString(KEY_NAME, binding.etInputFilter.text.toString())
            ?.apply()
    }

    private fun getInitNumbers() =
        gson.fromJson(phoneBookJson, Array<PhoneBook>::class.java).asList()

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = phoneAdapter
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }
}