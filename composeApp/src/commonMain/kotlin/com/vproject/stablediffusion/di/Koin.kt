package com.vproject.stablediffusion.di

import com.vproject.stablediffusion.network.KtorStableDiffusionApi
import com.vproject.stablediffusion.network.StableDiffusionApi
import com.vproject.stablediffusion.repository.DefaultImageRepository
import com.vproject.stablediffusion.repository.ImageRepository
import com.vproject.stablediffusion.repository.TestModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin() {
    startKoin {
        modules(coreModule() + platformModule())
    }
}

fun coreModule() = module {
    // Network Dependencies
    single {
        HttpClient {
            install(ContentNegotiation) {
                json( Json { ignoreUnknownKeys = true })
            }
        }
    }

    single<StableDiffusionApi> { KtorStableDiffusionApi(client = get()) }

    // Repository Dependencies
    single<ImageRepository> { DefaultImageRepository(stableDiffusionApi = get())}

    factoryOf(::TestModel)
}