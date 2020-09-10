package com.example.flickrreplica.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.flickrreplica.R
import com.example.flickrreplica.model.ContainerPhoto
import kotlinx.android.synthetic.main.activity_single_image.*

class SingleImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_image)

        val currentCity = intent.extras?.get("currentItem") as ContainerPhoto
        newImage(currentCity)
    }

    private fun newImage(currentCity: ContainerPhoto) {
        Glide.with(this)
            .load(currentCity.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .into(singleImage)
    }
}