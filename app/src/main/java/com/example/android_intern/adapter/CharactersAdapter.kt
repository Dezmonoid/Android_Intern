package com.example.android_intern.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_intern.CharacterInterface
import com.example.android_intern.R
import com.example.android_intern.databinding.CharacterButtonBinding
import com.example.android_intern.databinding.CharacterItemBinding

const val ITEM_TYPE_CONTENT = 0
const val ITEM_TYPE_BUTTON = 1
const val ERROR_TAG = "Error"

class CharactersAdapter(
    private val onItemClick: (characters: CharacterInterface.Character) -> Unit,
    private val onButtonClick: () -> Unit
) :
    ListAdapter<CharacterInterface, RecyclerView.ViewHolder>(UserItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_BUTTON -> {
                val binding = CharacterButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ButtonViewHolder(binding)
            }

            ITEM_TYPE_CONTENT -> {
                val binding = CharacterItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CharactersViewHolder(binding)
            }

            else -> {
                error(parent.context.getString(R.string.error_type))
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE_BUTTON -> {
                (holder as ButtonViewHolder).bind(onButtonClick)
            }

            ITEM_TYPE_CONTENT -> {
                (holder as CharactersViewHolder).bind(getItem(position), onItemClick)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CharacterInterface.Character -> ITEM_TYPE_CONTENT
            is CharacterInterface.Button -> ITEM_TYPE_BUTTON
        }
    }
}

private class UserItemDiffCallback : DiffUtil.ItemCallback<CharacterInterface>() {
    override fun areItemsTheSame(
        oldItem: CharacterInterface,
        newItem: CharacterInterface
    ): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: CharacterInterface,
        newItem: CharacterInterface
    ): Boolean =
        oldItem == newItem
}

class ButtonViewHolder(private val binding: CharacterButtonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(onButtonClick: () -> Unit) {
        binding.btnNext.setOnClickListener {
            onButtonClick()
        }
    }
}

class CharactersViewHolder(private val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        character: CharacterInterface,
        onItemClick: (characters: CharacterInterface.Character) -> Unit
    ) {
        when (character) {
            is CharacterInterface.Character -> {
                binding.typeCharacter.text = character.typeCharacter
                binding.genderCharacter.text = character.genderCharacter
                Glide
                    .with(itemView)
                    .load(character.image)
                    .into(binding.imageCharacter)
                this.itemView.setOnClickListener {
                    onItemClick(character)
                }
            }

            is CharacterInterface.Button -> {
                error(R.string.unknown_error)
            }
        }
    }
}