package com.example.yacontacts

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yacontacts.database.ContactsViewModel
import com.example.yacontacts.databinding.ActivityMainBinding
import com.example.yacontacts.databinding.DialogueBinding

class MainActivity : AppCompatActivity(),IContactAdapter {

    private lateinit var viewmodel : ContactsViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermissions()

        binding.rv.layoutManager = LinearLayoutManager(this)
        viewmodel = ViewModelProvider(this)[ContactsViewModel::class.java]
        viewmodel.getAll.observe(this) { list ->
            binding.rv.adapter = ContactAdapter(this,list.reversed(),this)
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this,AddContact::class.java))
        }
    }

    override fun onItemClickedDelete(id: Int) {
        val builder = Dialog(this)
        val dialogueBinding = DialogueBinding.inflate(layoutInflater)
        builder.setContentView(dialogueBinding.root)
        builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.show()

        dialogueBinding.no.setOnClickListener {
            builder.dismiss()
        }
        dialogueBinding.yes.setOnClickListener {
            viewmodel.delete(id)
            builder.dismiss()
        }
    }

    private fun checkPermissions(){
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),101)
    }
}