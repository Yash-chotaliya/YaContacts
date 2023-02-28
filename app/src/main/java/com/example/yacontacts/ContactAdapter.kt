package com.example.yacontacts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yacontacts.database.Contacts
import kotlinx.android.synthetic.main.contactrvlay.view.*

class ContactAdapter(private val context: Context, private val list:List<Contacts>):RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contactrvlay,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
        holder.callbtn.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:"+ list[position].number)
            context.startActivity(callIntent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder (itemView : View):RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.name
        val number: TextView = itemView.number
        val callbtn : ImageView = itemView.callbtn
    }

}