package com.vproject.stablediffusion.app

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.stablediffusion.repository.userdata.UserDataRepository
import kotlinx.coroutines.launch

class AppModel(private val userDataRepository: UserDataRepository) :
    StateScreenModel<AppUiState>(
        AppUiState.Initial
    ) {

    fun getUserData() = screenModelScope.launch {
        userDataRepository.getUserData().collect { userData ->
            mutableState.value = AppUiState.Success(userData.darkThemeConfig)
        }
    }
}