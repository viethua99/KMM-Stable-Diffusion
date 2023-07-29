package com.vproject.brushai.feature.settings

import com.vproject.brushai.core.domain.GetUserEditableSettingsUseCase
import com.vproject.brushai.core.domain.SetDarkThemeConfigUseCase
import com.vproject.brushai.core.domain.SetPromptCfgScaleValueUseCase
import com.vproject.brushai.core.domain.SetPromptStepValueUseCase
import com.vproject.brushai.core.model.data.DarkThemeConfig.DARK
import com.vproject.brushai.core.model.data.DarkThemeConfig.LIGHT
import com.vproject.brushai.core.model.data.UserEditableSettings
import com.vproject.brushai.core.testing.repository.TestUserDataRepository
import com.vproject.brushai.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class SettingsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val userDataRepository = TestUserDataRepository()

    private val getUserEditableSettingsUseCase = GetUserEditableSettingsUseCase(userDataRepository)
    private val setPromptCfgScaleValueUseCase = SetPromptCfgScaleValueUseCase(userDataRepository)
    private val setPromptStepValueUseCase = SetPromptStepValueUseCase(userDataRepository)
    private val setDarkThemeConfigUseCase = SetDarkThemeConfigUseCase(userDataRepository)

    private lateinit var SUT: SettingsViewModel

    @Before
    fun setUp() {
        SUT = SettingsViewModel(
            getUserEditableSettingsUseCase,
            setPromptCfgScaleValueUseCase,
            setPromptStepValueUseCase,
            setDarkThemeConfigUseCase
        )
    }

    @Test
    fun whenScreenInitialized_thenStateIsLoading() = runTest {
        assertEquals(SettingsUiState.Loading, SUT.settingsUiState.value)
    }

    @Test
    fun whenUserDataLoaded_thenStateIsSuccess() = runTest {
        val collectJob =
            launch(mainDispatcherRule.testDispatcher) { SUT.settingsUiState.collect() }

        userDataRepository.setDarkThemeConfig(DARK)
        userDataRepository.setPromptCfgScaleValue(1f)
        userDataRepository.setPromptStepValue(1f)

        assertEquals(
            SettingsUiState.Success(
                UserEditableSettings(
                    darkThemeConfig = DARK,
                    promptCfgScaleValue = 1f,
                    promptStepValue = 1f
                ),
            ), SUT.settingsUiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun whenUpdatingPromptCfgScaleValue_thenUserDataIsUpdated() = runTest {
        val collectJob =
            launch(mainDispatcherRule.testDispatcher) { SUT.settingsUiState.collect() }

        userDataRepository.setDarkThemeConfig(DARK)
        userDataRepository.setPromptCfgScaleValue(1f)
        userDataRepository.setPromptStepValue(1f)

        SUT.updatePromptCfgScaleValue(5f)

        assertEquals(
            SettingsUiState.Success(
                UserEditableSettings(
                    darkThemeConfig = DARK,
                    promptCfgScaleValue = 5f,
                    promptStepValue = 1f
                ),
            ), SUT.settingsUiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun whenUpdatingPromptStepValue_thenUserDataIsUpdated() = runTest {
        val collectJob =
            launch(mainDispatcherRule.testDispatcher) { SUT.settingsUiState.collect() }

        userDataRepository.setDarkThemeConfig(DARK)
        userDataRepository.setPromptCfgScaleValue(1f)
        userDataRepository.setPromptStepValue(1f)

        SUT.updatePromptStepValue(5f)

        assertEquals(
            SettingsUiState.Success(
                UserEditableSettings(
                    darkThemeConfig = DARK,
                    promptCfgScaleValue = 1f,
                    promptStepValue = 5f
                ),
            ), SUT.settingsUiState.value
        )

        collectJob.cancel()
    }

    @Test
    fun whenUpdatingDarkModeConfig_thenUserDataIsUpdated() = runTest {
        val collectJob =
            launch(mainDispatcherRule.testDispatcher) { SUT.settingsUiState.collect() }

        userDataRepository.setDarkThemeConfig(DARK)
        userDataRepository.setPromptCfgScaleValue(1f)
        userDataRepository.setPromptStepValue(1f)

        SUT.updateDarkThemeConfig(LIGHT)

        assertEquals(
            SettingsUiState.Success(
                UserEditableSettings(
                    darkThemeConfig = LIGHT,
                    promptCfgScaleValue = 1f,
                    promptStepValue = 1f
                ),
            ), SUT.settingsUiState.value
        )

        collectJob.cancel()
    }
}