package com.example.android_intern


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
const val UNITS = "metric"
const val CITY_ID = "622034"
const val TAG = "Debug"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val setWeather = WeatherAdapter()
    private lateinit var apiService: WeatherApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWeatherRecyclerView()
        loadWeather()
    }

    private fun loadWeather() {
        if (WeatherStore.get() == null) {
            initApiService()
            setWeatherRecyclerView()
        } else {
            setWeather.submitList(WeatherStore.get())
        }
    }

    private fun setWeatherRecyclerView() {
        apiService.getCurrentForecastData(cityId = CITY_ID, appId = APP_ID, units = UNITS)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if (response.isSuccessful) {
                        val forecastList = response.body()?.list
                        WeatherStore.set(forecastList)
                        Log.d(TAG, binding.root.context.getString(R.string.connected))
                        runOnUiThread {
                            setWeather.submitList(forecastList)
                        }
                    } else {
                        Log.d(TAG, binding.root.context.getString(R.string.error_connected))
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.e(TAG, t.message, t)
                }
            })
    }

    private fun initApiService() {
        val retrofit = retrofitBuild()
        apiService = retrofit.create(WeatherApi::class.java)
    }


    private fun initWeatherRecyclerView() {
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.weatherRecyclerView.adapter = setWeather
    }

    private fun retrofitBuild(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getInterceptorClient())
        .build()

    private fun getInterceptorClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
        )
        .build()
}