package com.vproject.texttoimage.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vproject.texttoimage.core.database.dao.TextToImageDao
import com.vproject.texttoimage.core.database.entity.TextToImageEntity

@Database(entities = [TextToImageEntity::class], version = 1)
abstract class TextToImageDatabase : RoomDatabase() {
    abstract fun textToImageDao(): TextToImageDao
}