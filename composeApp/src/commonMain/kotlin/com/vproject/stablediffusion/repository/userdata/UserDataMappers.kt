package com.vproject.stablediffusion.repository.userdata

import com.vproject.stablediffusion.UserDataEntity
import com.vproject.stablediffusion.model.DarkThemeConfig
import com.vproject.stablediffusion.model.UserData

fun UserDataEntity.toUserData() = UserData(
    darkThemeConfig = DarkThemeConfig.valueOf(this.darkThemeConfig)
)

fun UserData.toUserDataEntity() = UserDataEntity(
    id = 0,
    darkThemeConfig = this.darkThemeConfig.name
)