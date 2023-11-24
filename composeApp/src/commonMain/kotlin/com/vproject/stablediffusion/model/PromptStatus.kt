package com.vproject.stablediffusion.model

enum class PromptStatus(val value: String) {
    Failed("failed"),
    Error("error"),
    Processing("processing"),
    Success("success"),
}