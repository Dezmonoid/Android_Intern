package com.example.android_intern

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


private const val URL =
    "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"
private const val NUMBER_OF_COUNT = 2
private const val TAG = "Result"
private const val VALUE = "Favorite"

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()
    private var receivedImageList = mutableListOf<String>()
    private var gson = Gson()
    private lateinit var request: Request

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editBinding()
        setContentView(binding.root)
        buildAndCallUrl()
    }

    private fun buildAndCallUrl() {
        request = Request.Builder()
            .url(URL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                fillingRecyclerView(response)
            }
        })
    }

    private fun fillingRecyclerView(response: Response) {
        val photo = gson.fromJson(response.body!!.string(), Photo::class.java)
        photo.photos.photo.forEach { photos ->
            receivedImageList.add(
                binding.root.context.getString(
                    R.string.icon_url,
                    photos.farm.toString(),
                    photos.server,
                    photos.id,
                    photos.secret
                )
            )
        }
        runOnUiThread()
        {
            binding.recyclerView.adapter = ImageAdapter(receivedImageList)
        }
    }

    private fun editBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = GridLayoutManager(this, NUMBER_OF_COUNT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.getStringExtra(TAG) == VALUE) {
            Snackbar.make(
                binding.root,
                binding.root.context.getText(R.string.add_favorite).toString(),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}