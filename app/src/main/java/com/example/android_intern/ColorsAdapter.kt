package com.example.android_intern

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(private val colors: List<String>) :
    RecyclerView.Adapter<ColorsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_color_layout, parent, false)
        return ColorsHolder(view)
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorsHolder, position: Int) {
        holder.bind(colors[position])
    }
}

class ColorsHolder(textView: View) : RecyclerView.ViewHolder(textView) {
    private val colorsText: TextView = textView.findViewById(R.id.text_view)
    fun bind(text: String) {
        colorsText.text = text
    }
}