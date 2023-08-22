package com.example.android_intern


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
const val UNITS = "metric"
const val CITY_ID = "622034"
const val SAVED_TAG = "Saved Json"
const val RETROFIT_TAG = "Connection"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = WeatherAdapter()
    private lateinit var apiService: WeatherApi
    private lateinit var savedJson: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            initApiService()
            initWeatherRecyclerView()
            callAndFillingWeather()
        } else {
            initWeatherRecyclerView()
            savedJson = savedInstanceState.getString(SAVED_TAG).toString()
            adapter.submitList(Gson().fromJson(savedJson, ForecastResponse::class.java).list)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVED_TAG, savedJson)
    }

    private fun callAndFillingWeather() {
        apiService.getCurrentForecastData(CITY_ID, APP_ID, UNITS)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if (response.isSuccessful) {
                        val forecastGetList = response.body()!!
                        savedJson = Gson().toJson(forecastGetList)
                        Log.i(RETROFIT_TAG, binding.root.context.getString(R.string.connected))
                        runOnUiThread {
                            adapter.submitList(forecastGetList.list)
                        }
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
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