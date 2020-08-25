package com.example.flickrreplica.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.flickrreplica.R
import com.example.flickrreplica.model.PhotoUrlModel
import kotlinx.android.synthetic.main.photo_card.view.*

class PhotoRecycAdapter(
    private val features: List<PhotoUrlModel>
) : RecyclerView.Adapter<PhotoRecycAdapter.PhotoViewHolder>() {
    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.photoView
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_card, parent, false)
        return PhotoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = features[position]
        Glide.with(holder.itemView.context)
            .load(currentItem.photoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(holder.photo)
    }

    override fun getItemCount(): Int {
        return features.size
    }
}