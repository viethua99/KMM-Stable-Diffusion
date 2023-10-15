package com.vproject.texttoimage.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vproject.texttoimage.core.database.entity.PromptEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [PromptDao] access
 */
@Dao
interface PromptDao {
    @Query(
        value = """
        SELECT * FROM prompt
        WHERE status = "success"
        ORDER BY id DESC
    """,
    )
    fun getGeneratedPromptEntities(): Flow<List<PromptEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnorePrompt(promptEntity: PromptEntity)
}