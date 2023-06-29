package com.example.android_intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val colorData: List<String> = arrayListOf(
            "Синий",
            "Красный",
            "Жёлтый",
            "Чёрный",
            "Белый",
            "Розовый",
            "Фиолетовый",
            "Зелёный",
            "Коричневый",
        )
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            ColorsAdapter(colorData) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }

    }
}


