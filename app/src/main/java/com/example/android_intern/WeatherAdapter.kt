package com.example.android_intern

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.WeatherItemColdBinding
import com.example.android_intern.databinding.WeatherItemHotBinding

private const val TYPE_COLD = 1
private const val TYPE_HOT = 2
private const val DEFAULT_TEMPERATURE = 0.0
private const val THRESHOLD_TEMPERATURE = -5

class WeatherAdapter() :
    ListAdapter<ForecastDataClass, RecyclerView.ViewHolder>(UserItemDiffCallback()) {
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
        val temp = getItem(position).temp ?: DEFAULT_TEMPERATURE
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
    fun bind(forecast: ForecastDataClass) {
        binding.tvDateTime.text = context.getString(
            R.string.date,
            forecast.dtTxt
        )
        binding.tvTemperature.text = context.getString(
            R.string.temperature,
            DecimalFormat(context.getString(R.string.format)).format(
                forecast.temp
            )
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
    fun bind(forecast: ForecastDataClass) {
        binding.tvDateTime.text = context.getString(
            R.string.date,
            forecast.dtTxt
        )
        binding.tvTemperature.text = context.getString(
            R.string.temperature,
            DecimalFormat(context.getString(R.string.format)).format(
                forecast.temp
            )
        )
    }
}

class UserItemDiffCallback : DiffUtil.ItemCallback<ForecastDataClass>() {
    override fun areItemsTheSame(
        oldItem: ForecastDataClass,
        newItem: ForecastDataClass
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ForecastDataClass,
        newItem: ForecastDataClass
    ): Boolean {
        return oldItem == newItem
    }
}