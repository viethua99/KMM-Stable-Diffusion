package com.vproject.texttoimage.core.network.model

import com.vproject.texttoimage.core.model.data.PromptData
import com.vproject.texttoimage.core.model.data.PromptStatus

data class TextToImageResponseBody(
    val id: Long,
    val status: String,
    val generateTime: Double?,
    val output: List<String>?
)

fun TextToImageResponseBody.toPromptData(): PromptData =
    PromptData(
        id,
        PromptStatus.values().find { it.value == status }
            ?: throw IllegalArgumentException("Illegal Argument Exception"),
        output?.firstOrNull(),
        "")