package com.vproject.texttoimage.core.network.model

data class TextToImageRequestBody(
    // Your API Key used for request authorization
    val key: String,
    // Text prompt with description of the things you want in the image to be generated.
    val prompt: String,
    // Items you don't want in the image.
    val negative_prompt: String = "",
    // Max Width: 1024x1024.
    val width: String = "512",
    // Max Height: 1024x1024.
    val height: String = "512",
    // Number of images to be returned in response. The maximum value is 4.
    val samples: String = "1",
    // Number of denoising steps. Available values: 21, 31, 41, 51.
    val num_inference_steps: String = "21",
    // A checker for NSFW images. If such an image is detected, it will be replaced by a blank image.
    val safety_checker: String = "no",
    // Enhance prompts for better results; default: yes, options: yes/no.
    val enhance_prompt: String = "yes",
    // Seed is used to reproduce results, same seed will give you same image in return again. Pass null for a random number.
    val seed: Int? = null,
    // Scale for classifier-free guidance (minimum: 1; maximum: 20).
    val guidance_scale: Double = 7.5,
    // Allow multi lingual prompt to generate images. Use "no" for the default English.
    val multi_lingual: String = "yes",
    // Set this parameter to "yes" to generate a panorama image.
    val panorama: String = "no",
    // If you want a high quality image, set this parameter to "yes". In this case the image generation will take more time.
    val self_attention: String = "yes",
    // Set this parameter to "yes" if you want to upscale the given image resolution two times (2x).
    val upscale: String = "yes",
    // This is used to pass an embeddings model (embeddings_model_id).
    val embeddings_model: String = "embeddings_model_id",
    // Set an URL to get a POST API call once the image generation is complete.
    val webhook: String? = null,
    // This ID is returned in the response to the webhook API call. This will be used to identify the webhook request.
    val track_id: String? = null
)