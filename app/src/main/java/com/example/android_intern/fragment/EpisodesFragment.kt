package com.example.android_intern.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.adapter.EpisodesAdapter
import com.example.android_intern.databinding.EpisodeFragmentBinding
import com.example.android_intern.viewModel.EpisodesViewModel

class EpisodesFragment : Fragment() {


    private var _binding: EpisodeFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = EpisodesAdapter()
    private val viewModel by viewModels<EpisodesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EpisodeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacterRecyclerView()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.liveData.observe(viewLifecycleOwner) { episodes ->
            adapter.submitList(episodes)
        }
    }


    private fun initCharacterRecyclerView() {
        binding.episodeRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.episodeRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}