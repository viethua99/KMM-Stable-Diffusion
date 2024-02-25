package com.vproject.stablediffusion.database

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

expect fun Scope.sqlDriverFactory(): SqlDriver

fun createDatabase(driver: SqlDriver): StableDiffusionDatabase {
    val database = StableDiffusionDatabase(
        driver = driver,
    )

    return database
}