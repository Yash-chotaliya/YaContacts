package com.example.yacontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yacontacts.database.ContactsViewModel
import com.example.yacontacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel : ContactsViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = LinearLayoutManager(this)
        viewmodel = ViewModelProvider(this)[ContactsViewModel::class.java]
        viewmodel.getAll.observe(this) { list ->
            binding.rv.adapter = ContactAdapter(this,list)
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this,AddContact::class.java))
        }
    }
}