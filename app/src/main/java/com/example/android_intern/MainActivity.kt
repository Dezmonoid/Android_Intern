package com.example.android_intern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


private const val URL =
    "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"
private const val EXTRA = "URL"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()
    private val receivedImageList = mutableListOf<String>()
    private val request = Request.Builder()
        .url(URL)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        fillRecyclerView()
    }

    private fun fillRecyclerView() {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val photo =
                    GsonBuilder().create()
                        .fromJson(response.body!!.string(), Photo::class.java)
                photo.photos.photo.forEach { photo ->
                    receivedImageList.add("https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg")
                }
                runOnUiThread()
                {
                    binding.recyclerView.adapter = ImageAdapter(receivedImageList, this@MainActivity)
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}