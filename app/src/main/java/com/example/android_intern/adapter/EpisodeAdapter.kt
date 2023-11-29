package com.example.android_intern.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_intern.Episode
import com.example.android_intern.databinding.EpisodeItemBinding

class EpisodeAdapter : ListAdapter<Episode.EpisodeItem, EpisodeViewHolder>(EpisodeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding =
            EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class EpisodeDiffCallback : DiffUtil.ItemCallback<Episode.EpisodeItem>() {
    override fun areItemsTheSame(
        oldItem: Episode.EpisodeItem,
        newItem: Episode.EpisodeItem
    ): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: Episode.EpisodeItem,
        newItem: Episode.EpisodeItem
    ): Boolean =
        oldItem == newItem
}

class EpisodeViewHolder(private val binding: EpisodeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(episode: Episode.EpisodeItem) {
        binding.nameEpisode.text = episode.name
        binding.dateEpisode.text = episode.airDate
    }
}