package com.vproject.texttoimage.core.network.model

data class TextToImageRequestBody(
    val key: String = "",
    val prompt: String = "",
    val negative_prompt: String? = null,
    val width: String = "1024",
    val height: String = "1024",
    val samples: String = "1",
    val num_inference_steps: String = "20",
    val safety_checker: String = "no",
    val enhance_prompt: String = "yes",
    val seed: Int? = null,
    val guidance_scale: Double = 7.5,
    val multi_lingual: String = "yes",
    val panorama: String = "no",
    val self_attention: String = "no",
    val upscale: String = "yes",
    val embeddings_model: String = "embeddings_model_id",
    val webhook: String? = null,
    val track_id: String? = null
)