package com.example.lorenzodwishlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var items: List<Item>
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailsRV = findViewById<RecyclerView>(R.id.emailsRv)
        var items : MutableList<Item> = ArrayList()
        val adapter = ItemAdapter(items)
        emailsRV.adapter = adapter
        emailsRV.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            (application as ItemApplication).db.itemDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    Item(
                        entity.name,
                        entity.price,
                        entity.url
                    )
                }.also { mappedList ->
                    items.clear()
                    items.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }
        val button = findViewById<Button>(R.id.button2)

        button.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }


    }

}