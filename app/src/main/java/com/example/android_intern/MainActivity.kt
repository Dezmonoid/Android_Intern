package com.example.android_intern


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.Gson


private const val PREFERENCES_NAME = "filterSetting"
private const val KEY_NAME = "filterText"
private const val CALL = "tel:"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreference: SharedPreferences
    private val gson = Gson()
    private val adapter = PhoneAdapter(){phoneBook -> callNumber(phoneBook) }
    private lateinit var loadFilter: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        initRecyclerView()
        loadFilterAndSetPhoneBook()
        setFilterListeners()
    }

    private fun callNumber(number: String) {
        val intent =
            Intent(Intent.ACTION_CALL, Uri.parse(CALL + number))
        ContextCompat.startActivity(binding.root.context, intent, null)
    }

    private fun loadFilterAndSetPhoneBook() {
        loadFilter = sharedPreference.getString(KEY_NAME, null).orEmpty()
        if (loadFilter.isEmpty()) {
            adapter.submitList(getInitNumbers())
        } else {
            adapter.submitList(getInitNumbers().filter {
                "${it.name} ${it.phone} ${it.type}".contains(
                    loadFilter
                )
            })
            binding.etInputFilter.setText(loadFilter)
        }
    }

    private fun setFilterListeners() {
        binding.etInputFilter.addTextChangedListener {
            val filteredList =
                getInitNumbers().filter { "${it.name} ${it.phone} ${it.type}".contains(binding.etInputFilter.text) }
            adapter.submitList(filteredList)
            savingCurrentFilter()
        }
    }

    private fun savingCurrentFilter() {
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
        binding.recyclerView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        savingCurrentFilter()
    }
}