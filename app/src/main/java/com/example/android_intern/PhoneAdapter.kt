package com.example.android_intern

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_intern.databinding.PhoneItemBinding

class PhoneAdapter :
    RecyclerView.Adapter<PhoneViewHolder>() {
    private var phoneBook: List<PhoneBook> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val binding = PhoneItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhoneViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return phoneBook.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(phoneBook[position])
    }

    fun setList(phoneBookExchange: List<PhoneBook>) {
        phoneBook = phoneBookExchange
        notifyDataSetChanged()
    }
}

class PhoneViewHolder(private val binding: PhoneItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(phoneBook: PhoneBook) {
        binding.tvName.text = binding.root.context.getString(
            R.string.name,
            phoneBook.name
        )
        binding.tvPhone.text = binding.root.context.getString(
            R.string.phone,
            phoneBook.phone
        )
        binding.tvType.text = binding.root.context.getString(
            R.string.type,
            phoneBook.type
        )
    }
}