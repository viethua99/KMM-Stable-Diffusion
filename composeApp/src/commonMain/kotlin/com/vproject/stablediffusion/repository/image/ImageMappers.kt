package com.vproject.stablediffusion.repository.image

import com.vproject.stablediffusion.model.GeneratedImageInfo
import com.vproject.stablediffusion.network.response.Artifact

fun Artifact.toGeneratedImageInfo() = GeneratedImageInfo(
    base64 = this.base64,
    finishReason = this.finishReason,
    seed = this.seed
)