package com.genesis.randomphoto.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.genesis.randomphoto.R
import com.genesis.randomphoto.dto.PhotoDTO

class SliderAdapter(private var context: Context, private var mList: ArrayList<PhotoDTO>) :
    RecyclerView.Adapter<SliderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, int: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_slide, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mList[position].id != 9999) {
            val URL = "https://picsum.photos/400/600?image=${mList[position].id}"
            // val options=RequestOptions.placeholderOf(R.drawable.background).error(R.drawable.background)
            //Glide.with(holder.itemView).load(URL).apply(options).into(holder.imageBackground)
            Glide.with(holder.itemView).load(URL).into(holder.imageBackground)
        } else {
            // Glide.with(holder.itemView).asGif().load("https://gifs.benlk.com/rubiks-loading.gif").into(holder.imageBackground)
            Glide.with(holder.itemView).load(R.drawable.loading).into(holder.imageBackground)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageBackground: ImageView

        init {
            super.itemView
            imageBackground = itemView.findViewById(R.id.img_bg)
        }
    }
}