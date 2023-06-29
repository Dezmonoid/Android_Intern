package com.example.android_intern

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(
    private val colors: List<String>,
    val callback: (String) -> Unit
) :
    RecyclerView.Adapter<ColorsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_colors_view, parent, false)
        return ColorsHolder(view)
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorsHolder, position: Int) {
        holder.recyclerText.text = colors[position]
        holder.recyclerText.setOnClickListener { callback(colors[position]) }
    }
}

class ColorsHolder(view: View) : RecyclerView.ViewHolder(view) {
    val recyclerText: TextView = view.findViewById(R.id.text_view)
}


