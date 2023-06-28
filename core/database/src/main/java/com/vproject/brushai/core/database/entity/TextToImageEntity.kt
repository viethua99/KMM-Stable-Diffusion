package com.vproject.brushai.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "textToImage")
data class TextToImageEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo
    val status: String,

    @ColumnInfo
    val generateTime: Double,
)