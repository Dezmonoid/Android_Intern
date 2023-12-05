package com.example.android_intern


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
private const val UNITS = "metric"
private const val CITY_ID = "622034"
private const val TAG = "Debug"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = WeatherAdapter()
    private lateinit var apiService: WeatherApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWeatherRecyclerView()
        initApiService()
        loadWeather()
    }

    private fun loadWeather() {
        if (WeatherStore.get().isEmpty()) {
            apiService.getCurrentForecastData(cityId = CITY_ID, appId = APP_ID, units = UNITS)
                .enqueue(object : Callback<ForecastResponse> {
                    override fun onResponse(
                        call: Call<ForecastResponse>,
                        response: Response<ForecastResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.d(TAG, binding.root.context.getString(R.string.connected))
                            val forecastList = response.body()?.list.orEmpty()
                            WeatherStore.set(forecastList)
                            adapter.submitList(forecastList)
                        } else {
                            Log.d(TAG, binding.root.context.getString(R.string.error_connected))
                        }
                    }

                    override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                        Log.e(TAG, t.message, t)
                    }
                })
        } else {
            adapter.submitList(WeatherStore.get())
        }
    }

    private fun initApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(WeatherApi::class.java)
    }

    private fun initWeatherRecyclerView() {
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.weatherRecyclerView.adapter = adapter
    }
}