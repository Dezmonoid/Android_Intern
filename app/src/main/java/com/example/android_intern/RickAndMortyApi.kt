package com.example.android_intern


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character?")
    fun getCharacter(): Call<Character>

    @GET("episode/{id}")
    fun getEpisode(
        @Path("id") postId: String
    ): Call<Episode>
}