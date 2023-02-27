package com.example.yacontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contactrvlay.view.*

class ContactAdapter(private val list:List<Contacts>):RecyclerView.Adapter<ContactAdapter.ViewHolder>(){
    class ViewHolder (itemView : View):RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.name
        val number: TextView = itemView.number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contactrvlay,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.number.text = list[position].number
    }

    override fun getItemCount(): Int {
        return list.size
    }

}