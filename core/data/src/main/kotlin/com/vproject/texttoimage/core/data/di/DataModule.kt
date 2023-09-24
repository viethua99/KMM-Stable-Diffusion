package com.vproject.texttoimage.core.data.di

import com.vproject.texttoimage.core.data.repository.image.ImageRepository
import com.vproject.texttoimage.core.data.repository.image.OfflineFirstImageRepository
import com.vproject.texttoimage.core.data.repository.style.OfflineFirstStyleRepository
import com.vproject.texttoimage.core.data.repository.style.StyleRepository
import com.vproject.texttoimage.core.data.repository.userdata.OfflineFirstUserDataRepository
import com.vproject.texttoimage.core.data.repository.userdata.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindsStyleRepository(
        styleRepository: OfflineFirstStyleRepository,
    ): StyleRepository

    @Binds
    fun bindsUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository,
    ): UserDataRepository

    @Binds
    fun bindsImageRepository(
        imageRepository: OfflineFirstImageRepository,
    ): ImageRepository
}