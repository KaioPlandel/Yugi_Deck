package com.plandel.compose002.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.plandel.compose002.R
import com.plandel.compose002.ui.AboutCardActivity

class AdapterWarrior : RecyclerView.Adapter<AdapterWarrior.MyViewHolder>() {

    var listImages: List<String> = emptyList()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image = itemView.findViewById<ImageView>(R.id.photoWarrior)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_all_warrior_cards,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var card = listImages[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(card)
                .into(holder.image)
        }

        holder.image.setOnClickListener {
            val intent = Intent(it.context, AboutCardActivity::class.java)
            intent.putExtra("card",card)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listImages.size
    }

    fun setData(lista: List<String>){
        listImages = lista
        Log.d("TAG", "setData: " + listImages.size)
        notifyDataSetChanged()
    }
}