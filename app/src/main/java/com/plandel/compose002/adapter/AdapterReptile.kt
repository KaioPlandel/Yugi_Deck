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
import com.plandel.compose002.model.CardImage
import com.plandel.compose002.ui.aboutCard.AboutCardActivity

class AdapterReptile : RecyclerView.Adapter<AdapterReptile.MyViewHolder>() {

    private var listImages: List<CardImage> = emptyList()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.photoReptile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_reptile_cards,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val card = listImages[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(card.image_url)
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

    fun setData(lista: List<CardImage>){
        listImages = lista
        Log.d("TAG", "setData: " + listImages.size)
        notifyDataSetChanged()
    }
}