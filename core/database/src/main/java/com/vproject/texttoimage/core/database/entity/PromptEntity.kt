package com.vproject.texttoimage.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vproject.texttoimage.core.model.data.PromptData
import com.vproject.texttoimage.core.model.data.PromptStatus

@Entity(tableName = "prompt")
data class PromptEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo
    val status: String,

    @ColumnInfo
    val imageUrl: String,

    @ColumnInfo
    val content: String,
)

fun PromptEntity.toPromptData(): PromptData =
    PromptData(id, PromptStatus.values().find { it.value == status }
        ?: throw IllegalArgumentException("Illegal Argument Exception"), imageUrl, content)

fun PromptData.toPromptEntity(): PromptEntity =
    PromptEntity(id, status.value, imageUrl ?: "", content)