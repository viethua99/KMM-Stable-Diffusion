package com.vproject.texttoimage.core.domain

import com.vproject.texttoimage.core.data.repository.userdata.UserDataRepository
import javax.inject.Inject

/**
 * A use case to set prompt step scale value.
 */
class SetPromptStepValueUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
) {
    suspend operator fun invoke(promptStepValue: Float) {
        userDataRepository.setPromptStepValue(promptStepValue)
    }
}