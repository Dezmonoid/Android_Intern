package com.example.android_intern

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhoneAdapter(private val phoneBook: List<PhoneBook>, val context: MainActivity) :
    RecyclerView.Adapter<PhoneViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        return PhoneViewHolder(LayoutInflater.from(context).inflate(R.layout.phone_item, parent, false))
    }

    override fun getItemCount(): Int {
        return phoneBook.size
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(phoneBook[position], context)
    }
}

class PhoneViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTV = view.findViewById<TextView>(R.id.TV1)
    val phoneTV = view.findViewById<TextView>(R.id.TV2)
    val typeTV = view.findViewById<TextView>(R.id.TV3)

    fun bind(phoneBook: PhoneBook, context: Context) {
        nameTV.text = "Name: ${phoneBook.name}"
        phoneTV.text = "Phone: ${phoneBook.phone}"
        typeTV.text = "Тype: ${phoneBook.type}"
    }
}