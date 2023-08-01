package com.example.android_intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

private const val URL =
    "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"
private const val TAG = "Message"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMessageToLog()
    }

    private fun getMessageToLog() {
        thread {
            val urlConnection = URL(URL).openConnection() as HttpURLConnection
            try {
                val readData = urlConnection.inputStream.bufferedReader().readText()
                Log.i(TAG, readData)
            } catch (e: Exception) {
                Log.e(TAG, e.stackTraceToString())
            } finally {
                urlConnection.disconnect();
            }
        }
    }

}
