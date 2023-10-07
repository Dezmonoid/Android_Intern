package com.example.android_intern


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://rickandmortyapi.com/api/"
const val TAG = "Error"
const val FIRST_PAGE = 1

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = CharacterAdapter()
    private lateinit var apiService: RickAndMortyApi
    private var page = FIRST_PAGE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initApiService()
        initCharacterRecyclerView()
        loadCharacter(FIRST_PAGE)
        setButtonListener()
    }

    private fun setButtonListener() {
        binding.nextPage.setOnClickListener {
            loadCharacter(++page)
        }
        binding.prevPage.setOnClickListener {
            loadCharacter(--page)
        }
    }

    private fun loadCharacter(pages: Int) {
        apiService.getCharacter(pages)
            .enqueue(object : Callback<Character> {
                override fun onResponse(
                    call: Call<Character>,
                    response: Response<Character>
                ) {
                    if (response.isSuccessful) {
                        val charactersList = response.body()?.results
                        runOnUiThread {
                            adapter.submitList(charactersList)
                        }
                    }
                }

                override fun onFailure(call: Call<Character>, t: Throwable) {
                    Log.e(TAG, t.message, t)
                }
            })
    }

    private fun initApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(RickAndMortyApi::class.java)
    }

    private fun initCharacterRecyclerView() {
        binding.characterRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.characterRecyclerView.adapter = adapter
    }
}