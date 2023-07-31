package com.example.android_intern


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = WeatherAdapter()
        binding.RV1.layoutManager = LinearLayoutManager(this)
        binding.RV1.adapter = adapter
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/").client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val weatherApi = retrofit.create(WeatherApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val weatherList = weatherApi.getWeather().await()
            Log.i("test", weatherList.weather.toString())

            runOnUiThread() {
                binding.apply {
                    adapter.submitList(weatherList.weather)
                }

            }
        }
    }
}