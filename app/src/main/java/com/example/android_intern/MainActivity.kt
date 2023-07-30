package com.example.android_intern


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

const val PhoneBookJson = """[{
    "name": "(Приёмная)",
    "phone": "+375 (2239) 7-17-80",
    "type": ""
  },
  {
    "name": "(Бухгалтерия)",
    "phone": "+375 (2239) 7-17-64",
    "type": ""
  },
  {
    "name": "(Бухгалтерия)",
    "phone": "+375 (2239) 7-18-08",
    "type": ""
  },
  {
    "name": "(Юридическое бюро)",
    "phone": "+375 (2239) 7-17-63",
    "type": ""
  },
  {
    "name": "(Отдел правовой и кадровой работы)",
    "phone": "+375 (2239) 7-17-93",
    "type": ""
  },
  {
    "name": "(Отдел материально-технического снабжения)",
    "phone": "+375 (2239) 7-18-12",
    "type": ""
  },
  {
    "name": "",
    "phone": "+375 44 712 36 26",
    "type": "Сектор сбыта бумаги"
  },
  {
    "name": "(Реализация на внутренний рынок)",
    "phone": "+375 (2239) 7-17-79",
    "type": "Сектор сбыта бумаги"
  },
  {
    "name": "(Реализация на внешний рынок)",
    "phone": "+375 (2239) 4-11-77",
    "type": "Сектор сбыта бумаги"
  },
  {
    "name": "(Реализация на внутренний рынок)",
    "phone": "+375 (2239) 7-18-25",
    "type": "Сектор сбыта бумаги"
  },
  {
    "name": "",
    "phone": "+375 44 580 09 70",
    "type": "Сектор сбыта продукции деревообработки"
  },
  {
    "name": "(Реализация продукции деревообработки)",
    "phone": "+375 (2239) 7-18-42",
    "type": "Сектор сбыта продукции деревообработки"
  },
  {
    "name": "(Реализация продукции деревообработки)",
    "phone": "+375 (2239) 3-64-71",
    "type": "Сектор сбыта продукции деревообработки"
  },
  {
    "name": "",
    "phone": "+375 29 352 25 20",
    "type": "Реализация домов, бань, беседок, клеёного бруса"
  },
  {
    "name": "",
    "phone": "+375 (2239) 7-18-43",
    "type": "Реализация домов, бань, беседок, клеёного бруса"
  },
  {
    "name": "(приемная)",
    "phone": "+375 (2239) 7-17-80",
    "type": "Факс"
  },
  {
    "name": "(отдел сбыта)",
    "phone": "+375 (2239) 7-17-79",
    "type": "Факс"
  },
  {
    "name": "(отдел материально-технического снабжения)",
    "phone": "+375 (2239) 7-17-82",
    "type": "Факс"
  },
  {
    "name": "(служба главного энергетика)",
    "phone": "+375 (2239) 7-18-06",
    "type": "Факс"
  }]"""

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerPhoneView: RecyclerView = findViewById(R.id.RV1)
        val filterText: EditText = findViewById(R.id.ET1)
        val gson = Gson()
        val phoneBook =
            gson.fromJson(PhoneBookJson, Array<PhoneBook>::class.java).asList()
        val phoneAdapter = PhoneAdapter(phoneBook,this)
        recyclerPhoneView.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = phoneAdapter
                    }

        filterText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                phoneAdapter.submitList(phoneBook.filter{ "${it.name} ${it.phone} ${it.type}".contains(filterText.text) })
            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

            }
        })


    }
}