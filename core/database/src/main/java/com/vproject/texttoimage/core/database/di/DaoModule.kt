package com.vproject.texttoimage.core.database.di

import com.vproject.texttoimage.core.database.TextToImageDatabase
import com.vproject.texttoimage.core.database.dao.TextToImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesTextToImageDao(
        database: TextToImageDatabase,
    ): TextToImageDao = database.textToImageDao()
}