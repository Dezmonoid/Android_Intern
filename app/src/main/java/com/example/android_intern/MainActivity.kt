package com.example.android_intern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        recyclerView.adapter = RecyclerAdapter(colorData)
    }

}

class RecyclerAdapter(private val colorData: List<String>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
    class RecyclerHolder(textView: View) : RecyclerView.ViewHolder(textView) {
        var recyclerText: TextView = textView.findViewById(R.id.text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val textView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return RecyclerHolder(textView)
    }

    override fun getItemCount(): Int = colorData.size

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.recyclerText.text = colorData[position]
    }
}
