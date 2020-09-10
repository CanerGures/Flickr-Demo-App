package com.example.flickrreplica.model

import java.io.Serializable

data class ContainerPhoto(
    val id: String,
    val url: String,
    val title: String
) : Serializable