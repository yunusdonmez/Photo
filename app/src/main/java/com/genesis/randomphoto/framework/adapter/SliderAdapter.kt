package com.genesis.randomphoto.framework.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.genesis.randomphoto.R

class SliderAdapter(private var context: Context, private var mList: ArrayList<Int>) :
    RecyclerView.Adapter<SliderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, int: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_slide, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageBackground.setImageResource(mList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageBackground: ImageView

        init {
            super.itemView
            imageBackground = itemView.findViewById(R.id.img_bg)
        }
    }
}