package com.vproject.stablediffusion.repository.image

import com.vproject.stablediffusion.ImageToImageEntity
import com.vproject.stablediffusion.TextToImageEntity
import com.vproject.stablediffusion.model.CanvasPreset
import com.vproject.stablediffusion.model.GeneratedImageInfo
import com.vproject.stablediffusion.model.ProjectInfo
import com.vproject.stablediffusion.network.response.Artifact
import com.vproject.stablediffusion.util.getCurrentTimeMillis
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

fun Artifact.toGeneratedImageInfo() = GeneratedImageInfo(
    base64 = this.base64,
    finishReason = this.finishReason,
    seed = this.seed
)

@OptIn(ExperimentalEncodingApi::class)
fun GeneratedImageInfo.toTextToImageEntity(prompt: String, styleId: String, width: Long, height: Long) = TextToImageEntity(
    id = -1,
    generatedImage = Base64.Default.decode(this.base64),
    finishReason = this.finishReason,
    seed = this.seed,
    prompt = prompt,
    styleId = styleId,
    width = width,
    height = height,
    timestamp = getCurrentTimeMillis()
)

fun TextToImageEntity.toProjectInfo() = ProjectInfo(
    id = this.id,
    projectType = "tti",
    generatedImage = this.generatedImage ?: byteArrayOf(),
    prompt = this.prompt,
    styleId = this.styleId,
    canvasId = CanvasPreset.entries.find { it.width == this.width && it.height == this.height }?.id ?: "",
    timestamp = this.timestamp
)

@OptIn(ExperimentalEncodingApi::class)
fun GeneratedImageInfo.toImageToImageEntity(originalImage: ByteArray, imageStrength: Double, prompt: String, styleId: String, width: Long, height: Long) = ImageToImageEntity(
    id = -1,
    originalImage = originalImage,
    generatedImage = Base64.Default.decode(this.base64),
    imageStrength = imageStrength,
    prompt = prompt,
    styleId = styleId,
    width = width,
    height = height,
    finishReason = this.finishReason,
    seed = this.seed,
    timestamp = getCurrentTimeMillis()
)

fun ImageToImageEntity.toProjectInfo() = ProjectInfo(
    id = this.id,
    projectType = "iti",
    originalImage = this.originalImage,
    generatedImage = this.generatedImage ?: byteArrayOf(),
    imageStrength = this.imageStrength,
    prompt = this.prompt,
    styleId = this.styleId,
    canvasId = CanvasPreset.entries.find { it.width == this.width && it.height == this.height }?.id ?: "",
    timestamp = this.timestamp
)