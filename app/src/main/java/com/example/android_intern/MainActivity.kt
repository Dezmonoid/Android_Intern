package com.example.android_intern


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
const val TAG_CONNECT = "Connection"
const val TAG_ERROR = "Error"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = WeatherAdapter()
    private lateinit var apiService: WeatherApi
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWeatherRecyclerView()
        if (savedInstanceState == null) {
            initApiService()
            callAndSetWeather()
        } else {
            getSavedWeather(savedInstanceState)
        }
    }

    private fun getSavedWeather(bundle: Bundle) {
        val savedJson = bundle.getString(SAVED_TAG).toString()
        val typeToken = object:TypeToken<List<ForecastResponse.Sky>>(){}.type
        adapter.submitList(gson.fromJson(savedJson, typeToken))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val gettingJson = gson.toJson(adapter.currentList)
        outState.putString(SAVED_TAG, gettingJson)
    }

    private fun callAndSetWeather() {
        apiService.getCurrentForecastData(CITY_ID, APP_ID, UNITS)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if (response.isSuccessful) {
                        val forecastGetList = response.body()!!
                        Log.d(TAG, binding.root.context.getString(R.string.connected))
                        runOnUiThread {
                            adapter.submitList(forecastGetList.list)
                        }
                    }
                    else {
                        Log.d(TAG, binding.root.context.getString(R.string.error_connect))
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.e(TAG,t.message,t)
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