package com.example.flickrreplica.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrreplica.R
import com.example.flickrreplica.adapter.PhotosAdapter
import com.example.flickrreplica.model.ContainerPhoto
import com.example.flickrreplica.util.PhotosViewModel

class MainActivity : AppCompatActivity() {
    private val photosViewModel: PhotosViewModel by viewModels()
    private val photosAdapter = PhotosAdapter()
    private var page: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //val nestedScroll: NestedScrollView = findViewById(R.id.scrollView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val photosAdapter = PhotosAdapter()


        recyclerView.adapter = photosAdapter
        val gridLayoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = gridLayoutManager

        val isLoading = false
        val isLastPage = false
        photosViewModel.loadPhotos(page).observe(this@MainActivity,
            Observer<List<ContainerPhoto>> { list ->
                with(photosAdapter) {
                    photos.addAll(list)
                    notifyDataSetChanged()
                }
            })


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                if (!recyclerView.canScrollVertically(1) && dy > 0) {

                    getData()
                    progressBar.visibility = View.GONE
                } else if (!recyclerView.canScrollVertically(-1) && dy < 0) {

                }

            }

        })
    }

    private fun getData() {
        page++

        photosViewModel.loadPhotos(page).observe(this,
            Observer<List<ContainerPhoto>> { list ->
                with(photosAdapter) {

                    photos.addAll(list)
                    notifyDataSetChanged()
                }
            })


    }
}