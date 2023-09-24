package com.vproject.texttoimage.feature.settings

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.vproject.texttoimage.core.model.data.DarkThemeConfig.DARK
import com.vproject.texttoimage.core.model.data.DarkThemeConfig.LIGHT
import com.vproject.texttoimage.core.model.data.UserEditableSettings
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenInitializing_thenDisplaySettingTopAppBar() {
        val settingsUiState = SettingsUiState.Loading
        launchSettingsScreen(composeTestRule, settingsUiState) {
        } verify {
            settingTopAppBarIsDisplayed()
            settingContentIsDisplayed()
        }
    }

    @Test
    fun whenLoadUserDataSucceeded_thenDisplayAdvancedPromptOptionsCard() {
        val settingsUiState = SettingsUiState.Success(UserEditableSettings(
            promptCfgScaleValue = 0f,
            promptStepValue = 0f,
            darkThemeConfig = DARK
        ))

        launchSettingsScreen(composeTestRule, settingsUiState) {
        } verify {
            advancedPromptOptionCardIsDisplayed()
        }
    }

    @Test
    fun whenLoadUserDataSucceeded_thenDisplayGeneralSettingCard() {
        val settingsUiState = SettingsUiState.Success(UserEditableSettings(
            promptCfgScaleValue = 0f,
            promptStepValue = 0f,
            darkThemeConfig = DARK
        ))

        launchSettingsScreen(composeTestRule, settingsUiState) {
        } verify {
            generalSettingCardIsDisplayed()
        }
    }

    @Test
    fun whenThemeIsDarkModeConfig_thenDarkModeSwitchIsOn() {
        val settingsUiState = SettingsUiState.Success(UserEditableSettings(
            promptCfgScaleValue = 0f,
            promptStepValue = 0f,
            darkThemeConfig = DARK
        ))

        launchSettingsScreen(composeTestRule, settingsUiState) {
        } verify {
            darkModeSwitchIsTurnedOn()
        }
    }

    @Test
    fun whenThemeIsLightModeConfig_thenDarkModeSwitchIsOff() {
        val settingsUiState = SettingsUiState.Success(UserEditableSettings(
            promptCfgScaleValue = 0f,
            promptStepValue = 0f,
            darkThemeConfig = LIGHT
        ))

        launchSettingsScreen(composeTestRule, settingsUiState) {
        } verify {
            darkModeSwitchIsTurnedOff()
        }
    }
}