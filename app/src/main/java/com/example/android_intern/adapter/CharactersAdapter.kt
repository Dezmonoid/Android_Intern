package com.example.android_intern.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_intern.Characters
import com.example.android_intern.databinding.CharacterItemBinding

class CharactersAdapter(
    private val onItemClick: (characters: Characters.Result) -> Unit
) :
    ListAdapter<Characters.Result, CharactersViewHolder>(UserItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}

private class UserItemDiffCallback : DiffUtil.ItemCallback<Characters.Result>() {
    override fun areItemsTheSame(oldItem: Characters.Result, newItem: Characters.Result): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Characters.Result,
        newItem: Characters.Result
    ): Boolean =
        oldItem == newItem
}

class CharactersViewHolder(private val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(characters: Characters.Result, onItemClick: (characters: Characters.Result) -> Unit) {
        binding.typeCharacter.text = characters.species
        binding.genderCharacter.text = characters.gender
        Glide
            .with(itemView)
            .load(characters.image)
            .into(binding.imageCharacter)
        this.itemView.setOnClickListener {
            onItemClick(characters)
        }
    }
}