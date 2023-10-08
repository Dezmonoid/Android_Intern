package com.example.android_intern


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
const val UNITS = "metric"
const val CITY_ID = "622034"
const val TAG = "Debug"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = WeatherAdapter()
    private val viewModel by viewModels<ForecastViewModel>()
    private lateinit var data: LiveData<List<ForecastResponse.Sky>?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWeatherRecyclerView()
        observeLiveData()
    }

    private fun observeLiveData() {
        data = viewModel.getData()
        data.observe(this) {
            adapter.submitList(data.value)
        }
    }

    private fun initWeatherRecyclerView() {
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.weatherRecyclerView.adapter = adapter
    }
}