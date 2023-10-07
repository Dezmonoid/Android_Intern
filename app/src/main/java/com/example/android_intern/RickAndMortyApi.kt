package com.example.android_intern


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character?")
    fun getCharacter(
        @Query("page") pageID: Int
    ): Call<Character>

    @GET("episode")
    fun getEpisode(): Call<Episode>
}