package com.example.android_intern

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class FullImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)
        Glide
            .with(this)
            .load(intent.getStringExtra("URL"))
            .into(findViewById(R.id.IV2))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.full_image_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent()
        intent.putExtra("result", "Like")
        setResult(RESULT_OK, intent)
        finish()
        return super.onOptionsItemSelected(item)
    }
}