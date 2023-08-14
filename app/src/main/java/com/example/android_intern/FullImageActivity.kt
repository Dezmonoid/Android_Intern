package com.example.android_intern

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.android_intern.databinding.ActivityFullImageBinding


private const val EXTRA = "URL"
private const val TAG = "Result"
private const val VALUE = "Favorite"

class FullImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getFullImage()
    }

    private fun getFullImage() {
        Glide
            .with(this)
            .load(intent.getStringExtra(EXTRA))
            .into(binding.fullImage)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.full_image_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent()
        intent.putExtra(TAG, VALUE)
        setResult(RESULT_OK, intent)
        finish()
        return super.onOptionsItemSelected(item)
    }
}