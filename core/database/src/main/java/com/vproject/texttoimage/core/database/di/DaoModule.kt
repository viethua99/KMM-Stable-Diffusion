package com.vproject.texttoimage.core.database.di

import com.vproject.texttoimage.core.database.TextToImageDatabase
import com.vproject.texttoimage.core.database.dao.PromptDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesPromptDao(
        database: TextToImageDatabase,
    ): PromptDao = database.promptDao()
}