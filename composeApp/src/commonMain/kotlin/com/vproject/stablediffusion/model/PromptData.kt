package com.vproject.stablediffusion.model

data class PromptData(
    val id: Long = 0,
    val status: PromptStatus,
    val imageUrl: String? = null,
    val content: String = ""
)