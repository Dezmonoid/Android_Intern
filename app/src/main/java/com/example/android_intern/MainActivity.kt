package com.example.android_intern


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
const val CITY_ID = "622034"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = WeatherAdapter()
    private val retrofit = initRetrofit()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        fillRecyclerView()
    }

    private fun fillRecyclerView() {
        retrofit.getCurrentForecastData(CITY_ID, APP_ID)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if (response.isSuccessful) {
                        val forecastGetList = response.body()!!
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

    private fun initRetrofit(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WeatherApi::class.java)
    }


    private fun initRecyclerView() {
        binding.weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.weatherRecyclerView.adapter = adapter
    }
}