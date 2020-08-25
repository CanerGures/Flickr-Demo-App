package com.example.flickrreplica.util

import com.example.flickrreplica.model.BasePhotosModel
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UrlInterface {
    @POST("services/rest/")
    fun getCredentials(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("per_page") per_page: Int,
        @Header("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int
    )

            : Call<BasePhotosModel>

}