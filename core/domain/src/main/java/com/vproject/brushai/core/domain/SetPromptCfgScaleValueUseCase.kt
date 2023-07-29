package com.vproject.brushai.core.domain

import com.vproject.brushai.core.data.repository.userdata.UserDataRepository
import javax.inject.Inject

/**
 * A use case to set prompt CFG scale value.
 */
class SetPromptCfgScaleValueUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
) {
    suspend operator fun invoke(promptCfgScaleValue: Float) {
        userDataRepository.setPromptCfgScaleValue(promptCfgScaleValue)
    }
}