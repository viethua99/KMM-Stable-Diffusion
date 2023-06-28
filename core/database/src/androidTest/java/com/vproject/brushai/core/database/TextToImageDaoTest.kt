package com.vproject.brushai.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vproject.brushai.core.database.dao.TextToImageDao
import com.vproject.brushai.core.database.entity.TextToImageEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class TextToImageDaoTest {
    private lateinit var SUT: TextToImageDao
    private lateinit var brushAiDatabase: BrushAiDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        brushAiDatabase = Room.inMemoryDatabaseBuilder(context, BrushAiDatabase::class.java).build()
        SUT = brushAiDatabase.textToImageDao()
    }

    @Test
    fun givenTextToImageDao_whenFetchingTextToImage_thenImageRetrievedSuccess() = runTest {
        val textToImageEntity = TextToImageEntity(
            id = 100,
            status = "",
            generateTime = 0.0
        )

        SUT.insertOrIgnoreTextToImage(textToImageEntity)

        val savedTextToImageEntity = SUT.getTextToImageEntity(100)

        assertEquals(100, savedTextToImageEntity.id)
    }
}
