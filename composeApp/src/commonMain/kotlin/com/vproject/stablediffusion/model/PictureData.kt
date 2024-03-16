package com.vproject.stablediffusion.model

import com.vproject.stablediffusion.util.createUUID
import kotlinx.serialization.Serializable

sealed interface PictureData {
    val name: String
    val description: String

    data class Resource(
        val resource: String,
        override val name: String,
        override val description: String,
    ) : PictureData

    @Serializable
    data class Camera(
        val id: String,
        override val name: String,
        override val description: String,
    ) : PictureData
}

fun createCameraPictureData(name: String, description: String) =
    PictureData.Camera(
        id = createUUID(),
        name = name,
        description = description,
    )
