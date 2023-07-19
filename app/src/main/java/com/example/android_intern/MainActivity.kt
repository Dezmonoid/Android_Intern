package com.example.android_intern

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


private const val URL =
    "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(URL)
            .build()
        val jsonImageList = mutableListOf<String>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val photo =
                    GsonBuilder().create()
                        .fromJson(response.body!!.string(), Photo::class.java)
                photo.photos.photo.forEach { photo ->
                    jsonImageList.add("https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg")
                }
                runOnUiThread()
                {
                    recyclerView.adapter = ImageAdapter(jsonImageList, this@MainActivity)
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.getStringExtra("result") == "Like") {
            val snackbar1 =
                Snackbar.make(
                    findViewById(R.id.main_layout),
                    "Картинка поместилась в избранное!",
                    Snackbar.LENGTH_SHORT
                )
            snackbar1.show()
        }
    }
}