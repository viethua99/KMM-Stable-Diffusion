package com.vproject.texttoimage.core.network.model

data class TextToImageResponseBody(
    val id: Long? = null,
    val status: String,
    val generateTime: Double,
    val output: List<String>
)