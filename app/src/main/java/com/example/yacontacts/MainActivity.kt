package com.example.yacontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list :List<Contacts> = listOf(
            Contacts("fsgfd","dsfdg"),
            Contacts("fsgfd","dsfdg"),
            Contacts("fsgfd","dsfdg"),
            Contacts("fsgfd","dsfdg"),
            Contacts("fsgfd","dsfdg"),
            Contacts("fsgfd","dsfdg"),
            Contacts("fsgfd","dsfdg"),
            Contacts("fsgfd","dsfdg"),
            Contacts("fsgfd","dsfdg"),
            Contacts("fsgfd","dsfdg")
        )
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = ContactAdapter(list)


        fab.setOnClickListener {
            startActivity(Intent(this,AddContact::class.java))
        }
    }
}