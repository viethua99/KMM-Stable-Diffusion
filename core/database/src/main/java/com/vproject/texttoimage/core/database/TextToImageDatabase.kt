package com.vproject.texttoimage.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vproject.texttoimage.core.database.dao.PromptDao
import com.vproject.texttoimage.core.database.entity.PromptEntity

@Database(entities = [PromptEntity::class], version = 1)
abstract class TextToImageDatabase : RoomDatabase() {
    abstract fun promptDao(): PromptDao
}