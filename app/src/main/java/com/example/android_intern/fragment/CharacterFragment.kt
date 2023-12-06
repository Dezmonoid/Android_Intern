package com.example.android_intern.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.App.Companion.apiService
import com.example.android_intern.Character
import com.example.android_intern.MainActivity
import com.example.android_intern.R
import com.example.android_intern.adapter.CharacterAdapter
import com.example.android_intern.databinding.CharacterFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val PREFIX = "https://rickandmortyapi.com/api/episode/"
const val TAG = "Debug"

class CharacterFragment :Fragment(){
    private var _binding: CharacterFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = CharacterAdapter { character ->
        (context as MainActivity).changeFragment(EpisodeFragment(character.episode?.map {
            it?.removePrefix(PREFIX)
        }?.joinToString(",", "", "") ?: ""))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("Test","1")
        _binding = CharacterFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacterRecyclerView()
        loadCharacter()
        Log.e("Test","2")
    }

    private fun loadCharacter() {
        apiService.getCharacter()
            .enqueue(object : Callback<Character> {
                override fun onResponse(
                    call: Call<Character>,
                    response: Response<Character>
                ) {
                    if (response.isSuccessful) {
                        val characterList = response.body()
                        Log.d(TAG, binding.root.context.getString(R.string.connected))
                        adapter.submitList(characterList?.results)
                    } else {
                        Log.d(TAG, binding.root.context.getString(R.string.error_connected))
                    }
                }

                override fun onFailure(call: Call<Character>, t: Throwable) {
                    Log.e(TAG, t.message, t)
                }
            })
    }

    private fun initCharacterRecyclerView() {
        binding.characterRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.characterRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        Log.e("Test","3")
    }
}