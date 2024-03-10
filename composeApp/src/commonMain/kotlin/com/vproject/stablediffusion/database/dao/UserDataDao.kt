package com.vproject.stablediffusion.database.dao

import app.cash.sqldelight.coroutines.asFlow
import com.vproject.stablediffusion.UserDataEntity
import com.vproject.stablediffusion.database.StableDiffusionDatabase
import com.vproject.stablediffusion.stableDiffusionDispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserDataDao(private val stableDiffusionDatabase: StableDiffusionDatabase) {
    private val query get() = stableDiffusionDatabase.userDataEntityQueries

    suspend fun insert(userDataEntity: UserDataEntity) = withContext(stableDiffusionDispatchers.io) {
        query.insert(userDataEntity)
    }

    suspend fun getUserDataById(id: Long) = withContext(stableDiffusionDispatchers.io) {
        query.selectUserDataById(id = id).asFlow().map { it.executeAsOneOrNull() }
    }
}