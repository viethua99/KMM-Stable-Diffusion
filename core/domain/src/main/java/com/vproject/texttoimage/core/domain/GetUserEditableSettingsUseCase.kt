package com.vproject.texttoimage.core.domain

import com.vproject.texttoimage.core.data.repository.userdata.UserDataRepository
import com.vproject.texttoimage.core.model.data.UserEditableSettings
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