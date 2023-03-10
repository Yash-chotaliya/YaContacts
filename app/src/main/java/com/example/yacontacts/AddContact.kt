package com.example.yacontacts

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.yacontacts.database.Contacts
import com.example.yacontacts.database.ContactsViewModel
import com.example.yacontacts.databinding.ActivityAddContactBinding

@Suppress("DEPRECATION")
class AddContact : AppCompatActivity() {

    private lateinit var binding : ActivityAddContactBinding
    private val gallaryPickCode = 100
    private lateinit var viewmodel: ContactsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[ContactsViewModel::class.java]

        binding.contactpic.setOnClickListener {
            uploadImage()
        }

    }

    private fun uploadImage(){
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, gallaryPickCode)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== RESULT_OK){
            if(requestCode == gallaryPickCode){
                binding.contactpic.setImageURI(data?.data)
            }
        }
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
        val x = binding.name.text.toString()
        val y = binding.number.text.toString()

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