package com.vproject.texttoimage.core.network.model

import com.vproject.texttoimage.core.model.data.PromptData
import com.vproject.texttoimage.core.model.data.PromptStatus

data class QueuedImageResponseBody(
    val id: Long,
    val status: String,
    val output: List<String>
)

fun QueuedImageResponseBody.toPromptData(): PromptData =
    PromptData(id, PromptStatus.values().find { it.value == status }
        ?: throw IllegalArgumentException("Illegal Argument Exception"), output.first(), "")