package com.example.android_intern

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val colorData: List<String>) :
    RecyclerView.Adapter<RecyclerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val textView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return RecyclerHolder(textView)
    }

    override fun getItemCount(): Int = colorData.size

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.recyclerText.text = colorData[position]
    }
}

class RecyclerHolder(textView: View) : RecyclerView.ViewHolder(textView) {
    val recyclerText: TextView = textView.findViewById(R.id.text_view)
}