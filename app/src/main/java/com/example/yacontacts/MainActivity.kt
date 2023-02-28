package com.example.yacontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yacontacts.database.ContactsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel : ContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        viewmodel = ViewModelProvider(this)[ContactsViewModel::class.java]
        viewmodel.getAll.observe(this) { list ->
            rv.adapter = ContactAdapter(list)
        }

        fab.setOnClickListener {
            startActivity(Intent(this,AddContact::class.java))
        }
    }
}