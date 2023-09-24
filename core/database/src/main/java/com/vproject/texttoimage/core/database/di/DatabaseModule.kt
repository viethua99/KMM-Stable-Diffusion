package com.vproject.texttoimage.core.database.di

import android.content.Context
import androidx.room.Room
import com.vproject.texttoimage.core.database.TextToImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesTextToImageDatabase(
        @ApplicationContext context: Context,
    ): TextToImageDatabase = Room.databaseBuilder(
        context,
        TextToImageDatabase::class.java,
        "text-to-image-database",
    ).build()
}