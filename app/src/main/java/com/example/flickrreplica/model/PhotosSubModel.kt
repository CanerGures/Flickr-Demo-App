package com.example.flickrreplica.model

import com.google.gson.annotations.SerializedName

data class PhotosSubModel(
    @SerializedName("id") val id: Int,
    @SerializedName("owner") val owner: String,
    @SerializedName("secret") val secret: String,
    @SerializedName("server") val server: Int,
    @SerializedName("farm") val farm: Int,
    @SerializedName("title") val title: String,
    @SerializedName("ispublic") val ispublic: Int,
    @SerializedName("isfriend") val isfriend: Int,
    @SerializedName("isfamily") val isfamily: Int
)