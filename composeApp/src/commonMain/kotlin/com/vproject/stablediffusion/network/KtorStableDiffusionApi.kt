package com.vproject.stablediffusion.network

import com.vproject.stablediffusion.BuildKonfig
import com.vproject.stablediffusion.network.request.TextPrompt
import com.vproject.stablediffusion.network.request.TextToImageRequest
import com.vproject.stablediffusion.network.response.ImageResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

/**
 * Ktor API declaration for Stable Diffusion Network API
 */
class KtorStableDiffusionApi(private val client: HttpClient) : StableDiffusionApi {
    companion object {
        private const val STABILITY_AI_BASE_URL = "https://api.stability.ai"
    }

    /**
     * Method to request generates and returns an image from a prompt API.
     *
     * @param prompt Text prompt with description of the things you want in the image to be generated.
     * @param stylePreset The selected style preset.
     * @param width width of the image
     * @param height height of the image
     *
     * @return text to image response body.
     */
    override suspend fun postTextToImage(
        prompt: String,
        stylePreset: String,
        width: Long,
        height: Long
    ): ImageResponse {
        return requestHandler {
            client.post("$STABILITY_AI_BASE_URL/v1/generation/stable-diffusion-xl-1024-v1-0/text-to-image") {
                contentType(ContentType.Application.Json)
                header("Authorization", BuildKonfig.STABLE_DIFFUSION_API_KEY)
                setBody(
                    TextToImageRequest(
                        text_prompts = listOf(TextPrompt(prompt, 1f)),
                        style_preset = stylePreset,
                        width = width,
                        height = height
                    )
                )
            }
        }
    }

    /**
     * Method to request generates and returns an image from an original image API.
     *
     * @param originalImageByteArray original image byte array that need to generate.
     * @param prompt Text prompt with description of the things you want in the image to be generated.
     * @param imageStrength How much influence the image has on the diffusion process.
     * @param stylePreset The selected style preset.
     *
     * @return image to image response body.
     */
    override suspend fun postImageToImage(
        originalImageByteArray: ByteArray,
        imageStrength: Double,
        prompt: String,
        stylePreset: String
    ): ImageResponse {
        return requestHandler {
            client.post("$STABILITY_AI_BASE_URL/v1/generation/stable-diffusion-xl-1024-v1-0/image-to-image") {
                contentType(ContentType.Application.Json)
                header("Authorization", BuildKonfig.STABLE_DIFFUSION_API_KEY)
                setBody(MultiPartFormDataContent(
                    formData {
                        append("init_image", originalImageByteArray, Headers.build {
                            append(HttpHeaders.Accept, "application/json")
                        })
                        append("text_prompts[0][text]", prompt)
                        append("text_prompts[0][weight]", 1)
                        append("style_preset", stylePreset)
                        append("image_strength", 0.45)
                    }
                ))
            }
        }
    }
}