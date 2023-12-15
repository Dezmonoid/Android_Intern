package com.example.android_intern.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.MainActivity
import com.example.android_intern.adapter.CharactersAdapter
import com.example.android_intern.databinding.CharacterFragmentBinding
import com.example.android_intern.viewModel.CharactersViewModel
import com.example.android_intern.viewModel.EpisodesViewModel
import com.example.android_intern.viewModel.PREFIX

class CharactersFragment : Fragment() {
    private var _binding: CharacterFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = CharactersAdapter { character ->
        EpisodesViewModel.episode = character.episode?.map {
            it?.removePrefix(PREFIX)
        }?.joinToString() ?: ""
        Log.e("Episode", character.id.toString())
        Log.e("Episode", EpisodesViewModel.episode)
        (context as MainActivity).setFragment(EpisodesFragment())
    }
    private val viewModel by viewModels<CharactersViewModel>()

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
        observeLiveData()
        binding.btnNext.setOnClickListener {
            viewModel.nextPage()
        }
    }

    private fun observeLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) { characters ->
            adapter.submitList(characters.results)
        }
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