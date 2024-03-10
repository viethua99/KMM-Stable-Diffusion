package com.vproject.stablediffusion.repository.image

import com.vproject.stablediffusion.ImageEntity
import com.vproject.stablediffusion.model.GeneratedImageInfo
import com.vproject.stablediffusion.model.ProjectInfo
import com.vproject.stablediffusion.network.response.Artifact
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

fun Artifact.toGeneratedImageInfo() = GeneratedImageInfo(
    base64 = this.base64,
    finishReason = this.finishReason,
    seed = this.seed
)

fun ImageEntity.toProjectInfo() = ProjectInfo(
    id = this.id,
    projectType = this.projectType,
    originalImage = this.originalImage,
    generatedImage = this.generatedImage,
    prompt = this.prompt,
    styleId = this.styleId,
    canvasId = this.canvasId
)

@OptIn(ExperimentalEncodingApi::class)
fun GeneratedImageInfo.toImageEntity() = ImageEntity(
    id = -1,
    projectType = "",
    originalImage = null,
    generatedImage = Base64.Default.decode(this.base64),
    finishReason = this.finishReason,
    seed = this.seed,
    prompt = "",
    styleId = "",
    canvasId = ""
)

@OptIn(ExperimentalEncodingApi::class)
fun GeneratedImageInfo.toProjectInfo() = ProjectInfo(
    id = -1,
    projectType = "",
    originalImage = null,
    generatedImage = Base64.Default.decode(this.base64),
    prompt = "",
    styleId = "",
    canvasId = ""
)