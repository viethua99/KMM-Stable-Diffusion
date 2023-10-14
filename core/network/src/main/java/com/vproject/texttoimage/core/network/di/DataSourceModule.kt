package com.vproject.texttoimage.core.network.di

import com.vproject.texttoimage.core.network.TextToImageNetworkDataSource
import com.vproject.texttoimage.core.network.retrofit.RetrofitTextToImageNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {
    @Binds
    fun RetrofitTextToImageNetwork.binds(): TextToImageNetworkDataSource
}