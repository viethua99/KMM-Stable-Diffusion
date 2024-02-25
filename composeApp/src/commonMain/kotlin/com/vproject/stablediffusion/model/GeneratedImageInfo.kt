package com.vproject.stablediffusion.model

data class GeneratedImageInfo(
    val base64: String,
    val finishReason: String,
    val seed: Long
)