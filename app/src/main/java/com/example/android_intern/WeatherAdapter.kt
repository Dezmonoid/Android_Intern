package com.example.android_intern

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter:
    ListAdapter<Weather, WeatherViewHolder>(Comparator()) {


    class Comparator : DiffUtil.ItemCallback<Weather>(){
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Weather,
            newItem: Weather
        ): Boolean {
            return oldItem == newItem
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false))
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val dateTV = view.findViewById<TextView>(R.id.TV1)
    val timeTV = view.findViewById<TextView>(R.id.TV2)
    val temperatureTV = view.findViewById<TextView>(R.id.TV3)
    val pressureTV = view.findViewById<TextView>(R.id.TV4)

    fun bind(weatherList: Weather) {
    }
}