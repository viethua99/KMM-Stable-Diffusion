package com.vproject.stablediffusion.di

import com.vproject.stablediffusion.BuildKonfig
import com.vproject.stablediffusion.database.createDatabase
import com.vproject.stablediffusion.database.dao.MyDao
import com.vproject.stablediffusion.database.sqlDriverFactory
import com.vproject.stablediffusion.network.KtorStableDiffusionApi
import com.vproject.stablediffusion.network.StableDiffusionApi
import com.vproject.stablediffusion.repository.image.ImageRepositoryImpl
import com.vproject.stablediffusion.repository.image.ImageRepository
import com.vproject.stablediffusion.presentation.screen.generate.GenerateModel
import com.vproject.stablediffusion.presentation.screen.project.ProjectModel
import com.vproject.stablediffusion.presentation.screen.setting.SettingModel
import com.vproject.stablediffusion.presentation.screen.home.HomeModel
import com.vproject.stablediffusion.presentation.screen.detail.DetailModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            viewModelModule(),
            repositoryModule(),
            remoteModule(),
            localModule(),
            platformModule()
        )
    }
}

// called by iOS client
fun initKoin() = initKoin() {}

fun viewModelModule() = module {
    factoryOf(::HomeModel)
    factoryOf(::ProjectModel)
    factoryOf(::GenerateModel)
    factoryOf(::SettingModel)
    factoryOf(::DetailModel)
}

fun repositoryModule() = module {
    single<ImageRepository> { ImageRepositoryImpl(stableDiffusionApi = get())}
}

fun remoteModule() = module {
    // Network Dependencies
    single {
        HttpClient {
            install(ContentNegotiation) {
                json( Json { ignoreUnknownKeys = true })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 120000
                connectTimeoutMillis = 120000
                socketTimeoutMillis = 120000
            }

//            install(DefaultRequest) {
//                headers {
//                    append("Authorization", BuildKonfig.STABLE_DIFFUSION_API_KEY)
//                }
//            }
        }
    }

    single<StableDiffusionApi> { KtorStableDiffusionApi(client = get()) }
}

fun localModule() = module {
    factory { sqlDriverFactory() }
    single { createDatabase(driver = get()) }
    single { MyDao(stableDiffusionDatabase = get()) }
}