package com.example.android_intern

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.WeatherItemBinding

class WeatherAdapter() :
    ListAdapter<ForecastResponse.Sky, WeatherViewHolder>(UserItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class WeatherViewHolder(private val binding: WeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(forecast: ForecastResponse.Sky) {
        binding.tvDateTime.text = binding.root.context.getString(
            R.string.date,
            forecast.dtTxt
        )
        binding.tvTemperature.text = binding.root.context.getString(
            R.string.temperature,
            DecimalFormat(binding.root.context.getString(R.string.format)).format(
                forecast.main?.temp?.minus(
                    273.15
                )
            )
        )
        binding.tvPressure.text = binding.root.context.getString(
            R.string.pressure,
            forecast.main?.pressure.toString()
        )

        Glide
            .with(itemView)
            .load(
                binding.root.context.getString(
                    R.string.icon_url,
                    forecast.weather?.get(0)!!.icon
                )
            )
            .into(binding.ivIcon)
    }
}

class UserItemDiffCallback : DiffUtil.ItemCallback<ForecastResponse.Sky>() {
    override fun areItemsTheSame(
        oldItem: ForecastResponse.Sky,
        newItem: ForecastResponse.Sky
    ): Boolean {
        return oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(
        oldItem: ForecastResponse.Sky,
        newItem: ForecastResponse.Sky
    ): Boolean {
        return oldItem == newItem
    }
}