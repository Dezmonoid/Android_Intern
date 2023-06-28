package com.example.android_intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import timber.log.*
private const val TAG = "Intern"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonOk: Button = findViewById(R.id.button_ok)
        val editText: EditText = findViewById(R.id.edit_text)
        buttonOk.setOnClickListener {
            Timber.tag(TAG).i(editText.text.toString())
            Log.i("Task5", "projectInfo")
        }

    }
}