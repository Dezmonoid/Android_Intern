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

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreference: SharedPreferences
    private val gson = Gson()
    private val phoneAdapter = PhoneAdapter()
    private lateinit var loadFilter: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        initRecyclerView()
        loadFilterAndSetPhoneBook()
        setListeners()
    }

    private fun loadFilterAndSetPhoneBook() {
        loadFilter = sharedPreference.getString(KEY_NAME, null).orEmpty()
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
            saveStateFilter()
        }
    }

    private fun saveStateFilter() {
        sharedPreference
            .edit()
            .putString(KEY_NAME, binding.etInputFilter.text.toString())
            .apply()
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
        saveStateFilter()
    }
}