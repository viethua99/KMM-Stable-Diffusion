package com.vproject.stablediffusion.di

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
    single<StableDiffusionApi> { KtorStableDiffusionApi(client = get()) }
}

fun localModule() = module {
    factory { sqlDriverFactory() }
    single { createDatabase(driver = get()) }
    single { MyDao(stableDiffusionDatabase = get()) }
}