package com.example.android_intern

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

private const val UnitProgress = 10

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonOk: Button = findViewById(R.id.button)
        val checkBox: CheckBox = findViewById(R.id.check_box)
        val textView: TextView = findViewById(R.id.text_view)
        val userText: EditText = findViewById(R.id.edit_text)
        val loading: ProgressBar = findViewById(R.id.progress_bar)
        buttonOk.setOnClickListener {
            if (checkBox.isChecked) {
                textView.text = userText.text
                loading.progress += UnitProgress
            }
        }
    }
}