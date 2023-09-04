package com.example.android_intern

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.ImageAdapterBinding

class ImageAdapter(

    private val onItemClick: (url: String) -> Unit
) :
    RecyclerView.Adapter<ImageHolder>() {
    private var images: List<String> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding =
            ImageAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(binding)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(images[position], onItemClick)
    }

    fun setList(values: List<String>) {
        images = values
        notifyDataSetChanged()
    }

}

class ImageHolder(private val binding: ImageAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(imageUrl: String, onItemClick: (url: String) -> Unit) {
        Glide
            .with(binding.root.context)
            .load(imageUrl)
            .into(binding.imageView)
        binding.imageView.setOnClickListener {
            onItemClick(imageUrl)
        }
    }
}