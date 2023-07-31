package com.example.android_intern

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_intern.databinding.PhoneItemBinding


class PhoneAdapter :
    ListAdapter<PhoneBook, PhoneViewHolder>(UserItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val binding = PhoneItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class UserItemDiffCallback : DiffUtil.ItemCallback<PhoneBook>() {
    override fun areItemsTheSame(oldItem: PhoneBook, newItem: PhoneBook): Boolean =
        oldItem.phone == newItem.phone

    override fun areContentsTheSame(oldItem: PhoneBook, newItem: PhoneBook): Boolean =
        oldItem == newItem

}

class PhoneViewHolder(private val binding: PhoneItemBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(phoneBook: PhoneBook) {
        binding.TV1.text = "Name: ${phoneBook.name}"
        binding.TV2.text = binding.root.context.getString(
            R.string.phone,
            phoneBook.phone
        )   //"Phone: ${phoneBook.phone}"
        binding.TV3.text = "Тype: ${phoneBook.type}"
    }
}
