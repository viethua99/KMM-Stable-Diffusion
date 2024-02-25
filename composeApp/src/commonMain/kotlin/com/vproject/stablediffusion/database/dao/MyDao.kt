package com.vproject.stablediffusion.database.dao

import com.vproject.stablediffusion.MyEntity
import com.vproject.stablediffusion.database.StableDiffusionDatabase
import com.vproject.stablediffusion.stableDiffusionDispatchers
import kotlinx.coroutines.withContext

class MyDao(private val stableDiffusionDatabase: StableDiffusionDatabase) {
    private val query get() = stableDiffusionDatabase.myEntityQueries

    suspend fun insert(pokemonEntity: MyEntity) = withContext(stableDiffusionDispatchers.io) {
        query.insert(pokemonEntity)
    }
}