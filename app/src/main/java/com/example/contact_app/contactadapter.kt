package com.example.contact_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Contactadapter (
    private val listofcontact:List<contacttype>
):RecyclerView.Adapter<Contactadapter.contactviewholder>(){
    class contactviewholder(
        view: View
    ):RecyclerView.ViewHolder(view){
        val image:ImageView= view.findViewById(R.id.img)
        val heading:TextView=view.findViewById(R.id.heading)
        val subheading:TextView=view.findViewById(R.id.subheading)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactviewholder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false)
        return contactviewholder(view)
    }

    override fun getItemCount(): Int {
        return listofcontact.size
    }

    override fun onBindViewHolder(holder: contactviewholder, position: Int) {
        holder.image.setImageURI(listofcontact[position].imageRes)
        holder.heading.text=listofcontact[position].Heading
        holder.subheading.text=listofcontact[position].SubHeading

    }

}