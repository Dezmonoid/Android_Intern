package com.example.android_intern.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_intern.App
import com.example.android_intern.Episodes
import com.example.android_intern.R
import com.example.android_intern.adapter.EpisodeAdapter
import com.example.android_intern.databinding.EpisodeFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeFragment(private var episode: String) : Fragment() {
    private var _binding: EpisodeFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = EpisodeAdapter()
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
        loadEpisode()
    }

    private fun loadEpisode() {
        App.apiService.getEpisode(episode)
            .enqueue(object : Callback<Episodes> {
                override fun onResponse(
                    call: Call<Episodes>,
                    response: Response<Episodes>
                ) {
                    if (response.isSuccessful) {
                        val episodeList = response.body()
                        Log.d(TAG, binding.root.context.getString(R.string.connected))
                        adapter.submitList(episodeList)
                    } else {
                        Log.d(TAG, binding.root.context.getString(R.string.error_connected))
                    }
                }

                override fun onFailure(call: Call<Episodes>, t: Throwable) {
                    Log.e(TAG, t.message, t)
                }
            })
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