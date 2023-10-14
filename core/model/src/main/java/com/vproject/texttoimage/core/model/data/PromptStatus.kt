package com.vproject.texttoimage.core.model.data

enum class PromptStatus(val value: String) {
    Failed("failed"),
    Error("error"),
    Processing("processing"),
    Success("success"),
}