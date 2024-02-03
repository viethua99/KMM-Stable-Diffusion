package com.vproject.stablediffusion.model

import com.vproject.stablediffusion.SharedRes
import dev.icerock.moko.resources.ImageResource

enum class StableDiffusionMode(val title: String, val imageResource: ImageResource, val explanation: String) {
    TEXT_TO_IMAGE(
        "Text To Image",
        SharedRes.images.img_ai_creation_tti,
        "Write a captivating prompt to generate incredible image."
    ),
    IMAGE_TO_IMAGE(
        "Image To Image",
        SharedRes.images.img_ai_creation_iti,
        "AI transforms your image to any style you desire."
    ),
    AI_INPAINT(
        "AI Inpaint",
        SharedRes.images.img_ai_creation_ai_inpaint,
        "Command the AI to meticulously alter any segment of your image."
    )
}