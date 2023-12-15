package com.example.android_intern

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://rickandmortyapi.com/api/"

interface RickAndMortyApi {
    companion object {
        fun createApiService(): RickAndMortyApi {
            val interceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(RickAndMortyApi::class.java)
        }
    }

    @GET("character?")
    suspend fun getCharacters(): Characters

    @GET("character?")
    suspend fun getCharactersPage(
        @Query("page") page: String
    ): Characters

    @GET("episode/{id}")
    suspend fun getEpisode(
        @Path("id") postId: String
    ): Episodes
}