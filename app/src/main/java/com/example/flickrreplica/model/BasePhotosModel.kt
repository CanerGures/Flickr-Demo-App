package com.example.flickrreplica.model

import com.google.gson.annotations.SerializedName

data class BasePhotosModel(
    @SerializedName("photos") val photos: PhotosModel,
    @SerializedName("stat") val stat: String
)