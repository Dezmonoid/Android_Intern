package com.example.android_intern

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://rickandmortyapi.com/api/"

interface RickAndMortyApi {
    companion object {
        fun createApiService(): RickAndMortyApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RickAndMortyApi::class.java)
        }
    }

    @GET("character?")
    suspend fun getCharacters(): Characters

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") postId: String
    ): Episodes
}