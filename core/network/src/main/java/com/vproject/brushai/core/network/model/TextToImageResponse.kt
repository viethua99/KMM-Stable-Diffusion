package com.vproject.brushai.core.network.model

data class TextToImageResponse(
    val id: Long? = null,
    val status: String,
    val generateTime: Double,
    val output: List<String>
)