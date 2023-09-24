package com.vproject.texttoimage.feature.generate

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule

internal typealias ComponentActivityTestRule = AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>

internal fun launchGenerateScreen(
    testRule: ComponentActivityTestRule,
    uiState: GenerateUiState,
    block: GenerateRobot.() -> Unit
): GenerateRobot {
    testRule.setContent {
        GenerateScreen(
            generateUiState = uiState,
            onStyleItemClick = { _, _ -> },
            onGenerateButtonClicked = {})
    }
    return GenerateRobot(testRule).apply(block)
}

internal class GenerateRobot(private val testRule: ComponentActivityTestRule) {

    fun typePrompt(content: String) {
        testRule.onNodeWithTag(GenerateTestTags.GeneratePromptTextField).performTextInput(content)
    }

    fun clearPrompt() {
        testRule.onNodeWithTag(GenerateTestTags.GeneratePromptTextField).performTextClearance()
    }

    fun clickGenerateButton() {
        testRule.onNodeWithTag(GenerateTestTags.GenerateImageButton).performClick()
    }

    infix fun verify(block: GenerateVerification.() -> Unit): GenerateVerification {
        return GenerateVerification(testRule).apply(block)
    }
}

internal class GenerateVerification(private val testRule: ComponentActivityTestRule) {
    fun generateContentIsDisplayed() {
        testRule.onNodeWithTag(GenerateTestTags.GenerateContent).assertIsDisplayed()
    }

    fun generateTextFieldIsDisplayed() {
        testRule.onNodeWithTag(GenerateTestTags.GeneratePromptTextField).assertIsDisplayed()

    }

    fun generateButtonIsDisplayed() {
        testRule.onNodeWithTag(GenerateTestTags.GenerateImageButton).assertIsDisplayed()
    }

    fun promptValueIsDisplayed(content: String) {
        testRule.onNodeWithTag(GenerateTestTags.GeneratePromptTextField).assert(hasText(content))
    }

    fun promptHintIsDisplayed() {
        val generateMainHint = testRule.activity.getString(R.string.generate_main_hint)
        val generateSubHint = testRule.activity.getString(R.string.generate_sub_hint)
        testRule.onNodeWithTag(GenerateTestTags.GeneratePromptTextField)
            .assert(hasText("$generateMainHint\n$generateSubHint"))
    }

    fun promptHintDoesNotExist() {
        val generateMainHint = testRule.activity.getString(R.string.generate_main_hint)
        val generateSubHint = testRule.activity.getString(R.string.generate_sub_hint)
        testRule.onNodeWithText(generateMainHint).assertDoesNotExist()
        testRule.onNodeWithText(generateSubHint).assertDoesNotExist()
    }

    fun generateButtonIsClickable() {
        val generate = testRule.activity.getString(R.string.generate)
        testRule.onNodeWithText(generate).assertHasClickAction()
    }
}