package com.example.android_intern

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(
    private val images: List<String>,
    private val context: Context
) :
    RecyclerView.Adapter<ImageHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.image_adapter, parent, false)
        return ImageHolder(view)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        Glide
            .with(context)
            .load(images[position])
            .into(holder.imageView)
        holder.imageView.setOnClickListener {
            val intent = Intent(context, FullImageActivity::class.java)
            intent.putExtra("URL", images[position])
            context.startActivity(intent)
        }
    }
}

class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.IV1)
}