package com.example.android_intern

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_intern.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.io.IOException


private const val URL =
    "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"
private const val NUMBER_OF_COUNT = 2
private const val REQUEST_CODE = 1001

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()
    private lateinit var request: Request
    private val adapter = ImageAdapter() { url ->
        startFullImageActivity(url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        loadImages()
    }

    private fun loadImages() {
        request = buildRequest()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Timber.e(e.cause, binding.root.context.getString(R.string.error_connect))
            }

            override fun onResponse(call: Call, response: Response) {
                val photo = Gson().fromJson(response.body?.string(), Photo::class.java)
                val images = photo.photos.photo.map {
                    it.toUrl()
                }
                runOnUiThread() {
                    adapter.setList(images)
                }
            }
        })
    }

    private fun buildRequest() = Request.Builder()
        .url(URL)
        .build()

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, NUMBER_OF_COUNT)
        binding.recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            binding.root.showSnackbar(getString(R.string.add_favorite))
        }
    }

    private fun startFullImageActivity(url: String) {
        val intent = Intent(binding.root.context, FullImageActivity::class.java)
        intent.putExtra(FullImageActivity.ARG_URL, url)
        startActivityForResult(intent, REQUEST_CODE)
    }
}
