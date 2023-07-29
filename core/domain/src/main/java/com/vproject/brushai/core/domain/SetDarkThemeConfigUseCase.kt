package com.vproject.brushai.core.domain

import com.vproject.brushai.core.data.repository.userdata.UserDataRepository
import com.vproject.brushai.core.model.data.DarkThemeConfig
import javax.inject.Inject

/**
 * A use case to set dark theme configuration of application.
 */
class SetDarkThemeConfigUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
) {
    suspend operator fun invoke(darkThemeConfig: DarkThemeConfig) {
        userDataRepository.setDarkThemeConfig(darkThemeConfig)
    }
}