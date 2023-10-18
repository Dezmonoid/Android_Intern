package com.example.android_intern.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.App.Companion.apiService
import com.example.android_intern.MainActivity
import com.example.android_intern.adapter.CharacterAdapter
import com.example.android_intern.databinding.CharacterFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterFragment : Fragment() {

    private lateinit var binding: CharacterFragmentBinding
    private val adapter = CharacterAdapter { character ->
        (context as MainActivity).changeFragment(EpisodeFragment(character.episode?.map{
            it?.removePrefix("https://rickandmortyapi.com/api/episode/")
        }?.joinToString(",","","") ?:""))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacterRecyclerView()
        loadCharacter()
    }

    private fun loadCharacter() {
        //withcontext
        CoroutineScope(Dispatchers.IO).launch {
            val characterList = apiService.getCharacter().execute().body()?.results
            CoroutineScope(Dispatchers.Main).launch {
                adapter.submitList(characterList)
            }
        }
    }


    private fun initCharacterRecyclerView() {
        binding.characterRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.characterRecyclerView.adapter = adapter
    }
}