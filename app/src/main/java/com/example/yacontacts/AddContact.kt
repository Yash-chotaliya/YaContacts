package com.example.yacontacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.yacontacts.database.Contacts
import com.example.yacontacts.database.ContactsViewModel
import kotlinx.android.synthetic.main.contactrvlay.*

class AddContact : AppCompatActivity() {
    private lateinit var viewmodel: ContactsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        viewmodel = ViewModelProvider(this)[ContactsViewModel::class.java]

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbarmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save -> savecontact()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun savecontact(){
        val x = name.text.toString()
        val y = number.text.toString()

        if(x.isEmpty() || y.isEmpty()){
            Toast.makeText(this,"Error : No data inserted",Toast.LENGTH_SHORT).show()
        }
        else{
            viewmodel.insert(Contacts(0,x,y))
            startActivity(Intent(this@AddContact,MainActivity::class.java))
            finish()
        }

    }
}