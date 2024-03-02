package com.vproject.stablediffusion.model

import com.vproject.stablediffusion.SharedRes
import dev.icerock.moko.resources.ImageResource


enum class StableDiffusionMode(val title: String, val beforeImageResource: ImageResource, val afterImageResource: ImageResource? = null, val explanation: String) {
    TEXT_TO_IMAGE(
        "Text To Image",
        SharedRes.images.img_ai_creation_tti,
        null,
        "Write a captivating prompt to generate incredible image"
    ),
    IMAGE_TO_IMAGE(
        "Image To Image",
        SharedRes.images.img_ai_creation_iti_before,
        SharedRes.images.img_ai_creation_iti_after,
        "AI transforms your image to any style you desire"
    ),
    AI_INPAINT(
        "AI Inpaint",
        SharedRes.images.img_ai_creation_ai_inpaint,
        null,
        "Command the AI to meticulously alter any segment of your image"
    )
}