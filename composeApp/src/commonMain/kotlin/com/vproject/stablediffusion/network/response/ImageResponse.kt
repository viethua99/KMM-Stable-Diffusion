package com.vproject.stablediffusion.network.response

import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val artifacts: List<Artifact>
)

@Serializable
data class Artifact(
    val base64: String,
    val finishReason: String,
    val seed: Long
)