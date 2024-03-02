package com.vproject.stablediffusion.network

import com.vproject.stablediffusion.BuildKonfig
import com.vproject.stablediffusion.network.request.TextPrompt
import com.vproject.stablediffusion.network.request.TextToImageRequest
import com.vproject.stablediffusion.network.response.TextToImageResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
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
     *
     * @return text to image response body.
     */
    override suspend fun postTextToImage(prompt: String, stylePreset: String): TextToImageResponse {
        return requestHandler {
            client.post("$STABILITY_AI_BASE_URL/v1/generation/stable-diffusion-xl-1024-v1-0/text-to-image") {
                contentType(ContentType.Application.Json)
                header("Authorization", BuildKonfig.STABLE_DIFFUSION_API_KEY)

                setBody(TextToImageRequest(
                    text_prompts = listOf(TextPrompt(prompt, 1f)),
                    style_preset = stylePreset,
                    width = 1024,
                    height = 1024
                ))
            }
        }
    }

    /**
     * Method to request generates and returns an image from an original image API.
     *
     * @param prompt Text prompt with description of the things you want in the image to be generated.
     * @param stylePreset The selected style preset.
     *
     * @return text to image response body.
     */
    override suspend fun postImageToImage(prompt: String, stylePreset: String): TextToImageResponse {
        return requestHandler {
            client.post("$STABILITY_AI_BASE_URL/v1/generation/stable-diffusion-xl-1024-v1-0/text-to-image") {
                contentType(ContentType.Application.Json)
                setBody(TextToImageRequest(
                    text_prompts = listOf(TextPrompt(prompt, 1f)),
                    style_preset = stylePreset,
                    width = 1024,
                    height = 1024
                ))
            }
        }
    }
}