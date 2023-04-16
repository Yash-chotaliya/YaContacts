package com.example.yacontacts

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.yacontacts.database.Contacts
import com.example.yacontacts.databinding.ContactrvlayBinding

class ContactAdapter(private val context: Context, private val list:List<Contacts>,private val listener:IContactAdapter):RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolder(ContactrvlayBinding.inflate(LayoutInflater.from(context),parent,false))
        binding.delete.setOnClickListener {
            listener.onItemClickedDelete(list[binding.adapterPosition].id)
        }
        return binding
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
        holder.callbtn.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(context,android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context,"Please Allow us to call directly from your mobile app settings",Toast.LENGTH_SHORT).show()
            }
            else{
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:"+ list[position].number)
                context.startActivity(callIntent)
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder (itemBinding: ContactrvlayBinding):RecyclerView.ViewHolder(itemBinding.root){
        val name: TextView = itemBinding.name
        val number: TextView = itemBinding.number
        val callbtn : ImageButton = itemBinding.callbtn
        val delete:ImageButton = itemBinding.delete
    }

}

interface IContactAdapter{
    fun onItemClickedDelete(id:Int)
}