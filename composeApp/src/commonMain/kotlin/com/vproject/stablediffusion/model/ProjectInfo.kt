package com.vproject.stablediffusion.model

data class ProjectInfo(
     val id: Long,
     val projectType: String,
     val originalImage: ByteArray? = null,
     val generatedImage: ByteArray,
     val imageStrength: Double? = null,
     val prompt: String,
     val styleId: String,
     val canvasId: String,
     val timestamp: Long,
)
