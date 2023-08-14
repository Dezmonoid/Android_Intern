package com.example.android_intern

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.ImageAdapterBinding

private const val LOAD_NAME = "URL"

class ImageAdapter(
    private val images: List<String>
) :
    RecyclerView.Adapter<ImageHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding =
            ImageAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageHolder(binding)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.bind(images[position])
    }
}

class ImageHolder(private val binding: ImageAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val intent = Intent(binding.root.context, FullImageActivity::class.java)
    fun bind(images: String) {
        Glide
            .with(binding.root.context)
            .load(images)
            .into(binding.imageView)
        binding.imageView.setOnClickListener {
            intent.putExtra(LOAD_NAME, images)
            binding.root.context.startActivity(intent)
        }
    }

}