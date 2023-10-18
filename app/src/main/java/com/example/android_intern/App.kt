package com.example.android_intern

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://rickandmortyapi.com/api/"

class App : Application() {
    companion object {
        var apiService = initApiService()
            private set

        private fun initApiService(): RickAndMortyApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RickAndMortyApi::class.java)
        }
    }
}