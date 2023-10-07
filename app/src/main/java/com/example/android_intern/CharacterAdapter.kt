package com.example.android_intern

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.CharacterItemBinding


class CharacterAdapter(
    //private val onItemClick: (character: Character) -> Unit
) :
    ListAdapter<Character.Result, CharacterViewHolder>(UserItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))//, onItemClick)
    }

}

private class UserItemDiffCallback : DiffUtil.ItemCallback<Character.Result>() {
    override fun areItemsTheSame(oldItem: Character.Result, newItem: Character.Result): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character.Result, newItem: Character.Result): Boolean =
        oldItem == newItem
}

class CharacterViewHolder(private val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character.Result){//, onItemClick: (character: Character) -> Unit) {
        binding.typeCharacter.text = character.species
        binding.genderCharacter.text = character.gender
        Glide
            .with(itemView)
            .load(character.image)
            .into(binding.imageCharacter)
//        this.itemView.setOnClickListener {
//            onItemClick(character)
//        }
    }
}