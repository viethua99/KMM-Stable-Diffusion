package com.vproject.brushai.core.network.di

import com.vproject.brushai.core.network.BrushAiNetworkDataSource
import com.vproject.brushai.core.network.retrofit.RetrofitBrushAiNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun RetrofitBrushAiNetwork.binds(): BrushAiNetworkDataSource
}