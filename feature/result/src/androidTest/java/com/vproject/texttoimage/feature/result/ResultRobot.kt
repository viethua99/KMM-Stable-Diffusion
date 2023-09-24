package com.vproject.texttoimage.feature.result

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

internal typealias ComponentActivityTestRule = AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>

internal fun launchResultScreen(
    testRule: ComponentActivityTestRule,
    uiState: ResultUiState,
    block: ResultRobot.() -> Unit
): ResultRobot {
    testRule.setContent {
        ResultScreen(resultUiState = uiState)
    }
    return ResultRobot(testRule).apply(block)
}

internal class ResultRobot(private val testRule: ComponentActivityTestRule) {
    infix fun verify(block: ResultVerification.() -> Unit): ResultVerification {
        return ResultVerification(testRule).apply(block)
    }
}

internal class ResultVerification(private val testRule: ComponentActivityTestRule) {

}