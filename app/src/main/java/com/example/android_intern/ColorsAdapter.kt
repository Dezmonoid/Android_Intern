package com.example.android_intern

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(
    private val colors: List<String>,
    private val callback: (String) -> Unit
) :
    RecyclerView.Adapter<ColorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_colors_view, parent, false)
        return ColorsViewHolder(view)
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        holder.bind(colors[position], callback)
    }
}

class ColorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val recyclerText: TextView = view.findViewById(R.id.text_view)
    fun bind(colors: String, callback: (String) -> Unit) {
        recyclerText.text = colors
        recyclerText.setOnClickListener { callback(colors) }
    }
}


