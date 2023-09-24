package com.vproject.texttoimage.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vproject.texttoimage.core.database.entity.TextToImageEntity

/**
 * DAO for [TextToImageEntity] access
 */
@Dao
interface TextToImageDao {
    @Query(
        value = """
        SELECT * FROM textToImage
        WHERE id = :id
    """
    )
    suspend fun getTextToImageEntity(id: Long): TextToImageEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreTextToImage(textToImageEntity: TextToImageEntity)
}