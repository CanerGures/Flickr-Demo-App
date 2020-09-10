package com.example.flickrreplica.model

import java.io.Serializable

data class UrlBuilderModel(
    val farmId: String,
    val serverId: String,
    val secretId: String,
    val id: String
)

data class ContainerPhoto(
    val id: String,
    val url: String,
    val title: String
) : Serializable