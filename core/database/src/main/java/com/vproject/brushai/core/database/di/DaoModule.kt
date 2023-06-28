package com.vproject.brushai.core.database.di

import com.vproject.brushai.core.database.BrushAiDatabase
import com.vproject.brushai.core.database.dao.TextToImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesTextToImageDao(
        database: BrushAiDatabase,
    ): TextToImageDao = database.textToImageDao()
}