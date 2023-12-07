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
import com.example.android_intern.adapter.EpisodesAdapter
import com.example.android_intern.databinding.EpisodeFragmentBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val SAVED_TAG_EPISODES = "Episodes"

class EpisodesFragment : Fragment() {
    companion object{
        var episode: String = ""
    }
    private var _binding: EpisodeFragmentBinding? = null
    private val binding get() = _binding!!
    private val gson = Gson()
    private val adapter = EpisodesAdapter()
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
        getCallEpisodes(savedInstanceState)
    }

    private fun getCallEpisodes(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            getCallEpisodes()
        } else {
            getSavedEpisodes(savedInstanceState)
        }
    }

    private fun getSavedEpisodes(bundle: Bundle) {
        val json = bundle.getString(SAVED_TAG_EPISODES).toString()
        val typeToken = object : TypeToken<List<Episodes.EpisodeItem>>() {}.type
        adapter.submitList(gson.fromJson(json, typeToken))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val json = gson.toJson(adapter.currentList)
        outState.putString(SAVED_TAG_EPISODES, json)
    }

    private fun getCallEpisodes() {
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