package com.example.android_intern.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.App.Companion.apiService
import com.example.android_intern.Characters
import com.example.android_intern.MainActivity
import com.example.android_intern.R
import com.example.android_intern.adapter.CharactersAdapter
import com.example.android_intern.databinding.CharacterFragmentBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val PREFIX = "https://rickandmortyapi.com/api/episode/"
const val TAG = "Debug"
const val SAVED_TAG_CHARACTERS = "Characters"

class CharactersFragment : Fragment() {
    private var _binding: CharacterFragmentBinding? = null
    private val binding get() = _binding!!
    private val gson = Gson()
    private val adapter = CharactersAdapter { character ->
        EpisodesFragment.episode = character.episode?.map {
            it?.removePrefix(PREFIX)
        }?.joinToString(",", "", "") ?: ""
        (context as MainActivity).setFragment(EpisodesFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CharacterFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacterRecyclerView()
        loadCharacters(savedInstanceState)
    }

    private fun loadCharacters(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            getCalledCharacters()
        } else {
            getSavedCharacters(savedInstanceState)
        }
    }

    private fun getSavedCharacters(bundle: Bundle) {
        val json = bundle.getString(SAVED_TAG_CHARACTERS).toString()
        val typeToken = object : TypeToken<List<Characters.Result>>() {}.type
        adapter.submitList(gson.fromJson(json, typeToken))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val json = gson.toJson(adapter.currentList)
        outState.putString(SAVED_TAG_CHARACTERS, json)
    }

    private fun getCalledCharacters() {
        apiService.getCharacters()
            .enqueue(object : Callback<Characters> {
                override fun onResponse(
                    call: Call<Characters>,
                    response: Response<Characters>
                ) {
                    if (response.isSuccessful) {
                        val characterList = response.body()
                        Log.d(TAG, binding.root.context.getString(R.string.connected))
                        adapter.submitList(characterList?.results)
                    } else {
                        Log.d(TAG, binding.root.context.getString(R.string.error_connected))
                    }
                }

                override fun onFailure(call: Call<Characters>, t: Throwable) {
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
    }
}