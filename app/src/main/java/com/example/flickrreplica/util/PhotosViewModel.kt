package com.example.flickrreplica.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrreplica.adapter.PhotosAdapter
import com.example.flickrreplica.model.ContainerPhoto
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {
    private val mutablePhotosLiveData = MutableLiveData<List<ContainerPhoto>>()
    val photosLiveData: LiveData<List<ContainerPhoto>> = mutablePhotosLiveData

    var photosAdapter = PhotosAdapter()

    fun loadPhotos(page: Int): LiveData<List<ContainerPhoto>> {
        viewModelScope.launch {
            val searchResponse = WebClient.client.fetchImages(page)
            val photosList = searchResponse.photos.photo.map { photo ->
                ContainerPhoto(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            mutablePhotosLiveData.postValue(photosList)
        }
        return photosLiveData
    }
}
/*class PhotosViewModel2 : ViewModel() {
    private val mutablePhotosLiveData = MutableLiveData<List<ContainerPhoto>>()
    val photosLiveData: LiveData<List<ContainerPhoto>> = mutablePhotosLiveData

    var photosAdapter = PhotosAdapter()
}
    suspend fun fetchImages(page: String): List<ContainerPhoto>{
        if(page.isBlank()){
            return emptyList()
        }
        val searchResponse = WebClient.client.fetchImages(page)
        return searchResponse.photos.photo.map { photo ->
            ContainerPhoto(
                id = photo.id,
                url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title
            )
        }

    }*/