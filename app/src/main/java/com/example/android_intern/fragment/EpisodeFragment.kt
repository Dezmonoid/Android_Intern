package com.example.android_intern.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.App
import com.example.android_intern.adapter.EpisodeAdapter
import com.example.android_intern.databinding.EpisodeFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeFragment(var episode: String) : Fragment() {
    private lateinit var binding: EpisodeFragmentBinding
    private val adapter = EpisodeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EpisodeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacterRecyclerView()
        loadEpisode()
    }

    private fun loadEpisode() {
        //withcontext
        CoroutineScope(Dispatchers.IO).launch {
            val episodeList = App.apiService.getEpisode(
                episode).execute().body()
            Log.i("Debug",episodeList.toString())
            CoroutineScope(Dispatchers.Main).launch {
                adapter.submitList(episodeList)
            }
        }
    }

    private fun initCharacterRecyclerView() {
        binding.episodeRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.episodeRecyclerView.adapter = adapter
    }

}