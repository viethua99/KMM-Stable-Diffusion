package com.vproject.brushai.core.database.di

import android.content.Context
import androidx.room.Room
import com.vproject.brushai.core.database.BrushAiDatabase
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
    fun providesBrushAiDatabase(
        @ApplicationContext context: Context,
    ): BrushAiDatabase = Room.databaseBuilder(
        context,
        BrushAiDatabase::class.java,
        "brush-ai-database",
    ).build()
}