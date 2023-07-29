package com.vproject.brushai.core.domain

import com.vproject.brushai.core.data.repository.userdata.UserDataRepository
import com.vproject.brushai.core.model.data.UserEditableSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * A use case to get current user editable settings
 */
class GetUserEditableSettingsUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
) {
    operator fun invoke(): Flow<UserEditableSettings> =
        userDataRepository.userData.map {
            UserEditableSettings(
                promptCfgScaleValue = it.promptCfgScaleValue,
                promptStepValue = it.promptStepValue,
                darkThemeConfig = it.darkThemeConfig,
            )
        }
}