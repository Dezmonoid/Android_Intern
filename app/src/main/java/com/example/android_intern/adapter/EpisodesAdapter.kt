package com.example.android_intern.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_intern.Episode
import com.example.android_intern.databinding.EpisodeItemBinding

class EpisodesAdapter : ListAdapter<Episode, EpisodeViewHolder>(EpisodeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding =
            EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(
        oldItem: Episode,
        newItem: Episode
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Episode,
        newItem: Episode
    ): Boolean =
        oldItem == newItem
}

class EpisodeViewHolder(private val binding: EpisodeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(episode: Episode) {
        binding.nameEpisode.text = episode.name
        binding.dateEpisode.text = episode.airDate
    }
}