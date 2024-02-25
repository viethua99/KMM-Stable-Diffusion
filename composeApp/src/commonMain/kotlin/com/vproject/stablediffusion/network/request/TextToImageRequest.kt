package com.vproject.stablediffusion.network.request

import kotlinx.serialization.Serializable

@Serializable
data class TextToImageRequest(
    val height: Int = 1024,
    val width: Int = 1024,
    val text_prompts: List<TextPrompt>,
    val cfg_scale: Int = 5,
    val clip_guidance_preset: String = "FAST_BLUE",
    val sampler: String = "K_DPM_2_ANCESTRAL",
    val samples: Int = 1,
    val steps: Int = 40,
    val style_preset: String,
)

@Serializable
data class TextPrompt(
    val text: String,
    val weight: Float
)

