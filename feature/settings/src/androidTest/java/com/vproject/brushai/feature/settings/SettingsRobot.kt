package com.vproject.brushai.feature.settings

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

internal typealias ComponentActivityTestRule = AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>

internal fun launchSettingsScreen(
    testRule: ComponentActivityTestRule,
    uiState: SettingsUiState,
    block: SettingsRobot.() -> Unit
): SettingsRobot {
    testRule.setContent {
        SettingsScreen(settingsUiState = uiState)
    }
    return SettingsRobot(testRule).apply(block)
}

internal class SettingsRobot(private val testRule: ComponentActivityTestRule) {
    infix fun verify(block: SettingsVerification.() -> Unit): SettingsVerification {
        return SettingsVerification(testRule).apply(block)
    }
}

internal class SettingsVerification(private val testRule: ComponentActivityTestRule) {

}