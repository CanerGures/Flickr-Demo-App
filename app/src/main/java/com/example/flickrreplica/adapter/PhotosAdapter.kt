package com.example.flickrreplica.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.flickrreplica.R
import com.example.flickrreplica.model.ContainerPhoto
import com.example.flickrreplica.ui.SingleImageActivity
import kotlinx.android.synthetic.main.photo_card.view.*

class PhotosAdapter(val photos: MutableList<ContainerPhoto> = mutableListOf()) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photo_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, SingleImageActivity::class.java)
            intent.putExtra("currentItem", photos[position])
            it.context.startActivity(intent)

        }
    }

    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: ContainerPhoto) {
            Glide.with(itemView.context)
                .load(photo.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(itemView.photoView)
        }

    }
}

const val IMAGE_SIDE_PX = 400