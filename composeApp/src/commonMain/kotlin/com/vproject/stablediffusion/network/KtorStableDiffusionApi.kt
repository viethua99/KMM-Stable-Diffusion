package com.vproject.stablediffusion.network

import com.vproject.stablediffusion.network.dto.TextToImageRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType

/**
 * Ktor API declaration for Stable Diffusion Network API
 */
class KtorStableDiffusionApi(private val client: HttpClient) : StableDiffusionApi {
    companion object {
        private const val STABLE_DIFFUSION_BASE_URL = "https://stablediffusionapi.com"
    }

    /**
     * Method to request generates and returns an image from a prompt API.
     *
     * @param prompt Text prompt with description of the things you want in the image to be generated.
     * @param negativePrompt Items you don't want in the image.
     *
     * @return text to image response body.
     */
    override suspend fun postTextToImage(prompt: String, negativePrompt: String) =
        client.post("$STABLE_DIFFUSION_BASE_URL/api/v3/text2img") {
            contentType(ContentType.Application.Json)
            setBody(TextToImageRequestDto(prompt = prompt, negativePrompt = negativePrompt))
        }.bodyAsText()

    /**
     * Method to request queued images from stable diffusion API.
     * Usually more complex image generation requests take more time for processing.
     * Such requests are being queued for processing and the output images are retrievable after some time.
     *
     * @param id The ID returned together with the image URL in the response upon its generation.
     *
     * @return queued image response body.
     */
    override suspend fun fetchQueuedImage(id: String) =
        client.post("$STABLE_DIFFUSION_BASE_URL/api/v3/fetch/$id").bodyAsText()
}