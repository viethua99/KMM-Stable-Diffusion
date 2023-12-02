package com.vproject.stablediffusion.model

enum class StableDiffusionMode(val title: String, val imageUrl: String, val explanation: String) {
    TEXT_TO_IMAGE(
        "Text To Image",
        "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png",
        "Write a captivating prompt to generate incredible image."
    ),
    IMAGE_TO_IMAGE(
        "Image To Image",
        "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png",
        "AI transforms your image to any style you desire"
    ),
    TEXT_TO_VIDEO(
        "Text To Video",
        "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png",
        "Write a captivating prompt to generate incredible video."
    )
}