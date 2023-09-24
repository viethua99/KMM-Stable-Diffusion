package com.vproject.texttoimage.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vproject.texttoimage.core.database.dao.TextToImageDao
import com.vproject.texttoimage.core.database.entity.TextToImageEntity
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class TextToImageDaoTest {
    private lateinit var SUT: TextToImageDao
    private lateinit var textToImageDatabase: TextToImageDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        textToImageDatabase = Room.inMemoryDatabaseBuilder(context, TextToImageDatabase::class.java).build()
        SUT = textToImageDatabase.textToImageDao()
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
