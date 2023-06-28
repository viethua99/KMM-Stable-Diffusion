package com.vproject.brushai.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vproject.brushai.core.database.dao.TextToImageDao
import com.vproject.brushai.core.database.entity.TextToImageEntity

@Database(entities = [TextToImageEntity::class], version = 1)
abstract class BrushAiDatabase : RoomDatabase() {
    abstract fun textToImageDao(): TextToImageDao
}