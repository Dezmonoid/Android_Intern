package com.example.android_intern.presentation.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_intern.R
import com.example.android_intern.databinding.WeatherItemColdBinding
import com.example.android_intern.databinding.WeatherItemHotBinding
import com.example.android_intern.presentation.model.ForecastUI

private const val TYPE_COLD = 1
private const val TYPE_HOT = 2
private const val THRESHOLD_TEMPERATURE = -5

class ForecastAdapter() :
    ListAdapter<ForecastUI, RecyclerView.ViewHolder>(UserItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_COLD -> {
                val binding = WeatherItemColdBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                WeatherColdViewHolder(binding)
            }

            TYPE_HOT -> {
                val binding = WeatherItemHotBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                WeatherHotViewHolder(binding)
            }

            else -> {
                error(parent.context.getString(R.string.error_type))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_COLD -> {
                (holder as WeatherColdViewHolder).bind(getItem(position))
            }

            TYPE_HOT -> {
                (holder as WeatherHotViewHolder).bind(getItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val temp = getItem(position).temp
        return if (temp < THRESHOLD_TEMPERATURE) {
            TYPE_COLD
        } else {
            TYPE_HOT
        }
    }
}

class WeatherColdViewHolder(private val binding: WeatherItemColdBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val context = binding.root.context
    fun bind(forecast: ForecastUI) {
        binding.tvDateTime.text = context.getString(
            R.string.date, forecast.dtTxt
        )
        binding.tvTemperature.text = context.getString(
            R.string.temperature, forecast.temp.toString()
        )
        Glide
            .with(itemView)
            .load(
                context.getString(
                    R.string.icon_url,
                    forecast.icon
                )
            )
            .into(binding.ivIcon)
    }
}


class WeatherHotViewHolder(private val binding: WeatherItemHotBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val context = binding.root.context
    fun bind(forecast: ForecastUI) {
        binding.tvDateTime.text = context.getString(
            R.string.date,
            forecast.dtTxt
        )
        binding.tvTemperature.text = context.getString(
            R.string.temperature, forecast.temp.toString()
        )
    }
}

class UserItemDiffCallback : DiffUtil.ItemCallback<ForecastUI>() {
    override fun areItemsTheSame(
        oldItem: ForecastUI,
        newItem: ForecastUI
    ): Boolean {
        return oldItem.dtTxt == newItem.dtTxt
    }

    override fun areContentsTheSame(
        oldItem: ForecastUI,
        newItem: ForecastUI
    ): Boolean {
        return oldItem == newItem
    }
}