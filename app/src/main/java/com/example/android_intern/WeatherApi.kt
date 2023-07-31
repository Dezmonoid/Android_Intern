package com.example.android_intern


import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather?id=622034&appid=eeba719e0ea1ed0d70d6ea433307695e")
    fun getWeather(): Call<Weathers>
}