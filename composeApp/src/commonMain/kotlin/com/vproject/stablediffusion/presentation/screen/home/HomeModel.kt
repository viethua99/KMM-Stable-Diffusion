package com.vproject.stablediffusion.presentation.screen.home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.stablediffusion.model.DarkThemeConfig
import com.vproject.stablediffusion.repository.userdata.UserDataRepository
import kotlinx.coroutines.launch

class HomeModel(private val userDataRepository: UserDataRepository) :
    StateScreenModel<HomeUiState>(HomeUiState.Initial) {

    fun getMainData() = screenModelScope.launch {
        userDataRepository.getUserData().collect { userData ->
            mutableState.value = HomeUiState.Success(userData.darkThemeConfig)
        }
    }

    fun toggleDarkThemeConfig(darkThemeConfig: DarkThemeConfig) = screenModelScope.launch {
        userDataRepository.setDarkThemeConfig(darkThemeConfig)
    }
}
