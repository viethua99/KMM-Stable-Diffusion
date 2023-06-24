package com.vproject.brushai.feature.prompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.test.ext.junit.rules.ActivityScenarioRule

internal typealias ComponentActivityTestRule = AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>

internal fun launchPromptScreen(
    testRule: ComponentActivityTestRule,
    uiState: PromptUiState,
    block: PromptRobot.() -> Unit
): PromptRobot {
    testRule.setContent {
        PromptScreen(promptUiState = uiState)
    }
    return PromptRobot(testRule).apply(block)
}

const val DEFAULT_PROMPT_SCALE = 7.5f
const val DEFAULT_PROMPT_STEP = 20

internal class PromptRobot(private val testRule: ComponentActivityTestRule) {

    fun typeMainPrompt(content: String) {
        val mainPromptHint = testRule.activity.getString(R.string.main_prompt_hint)
        testRule.onNodeWithTag(mainPromptHint).performTextInput(content)
    }

    fun typeNegativePrompt(content: String) {
        val negativePromptHint = testRule.activity.getString(R.string.negative_prompt_hint)
        testRule.onNodeWithTag(negativePromptHint).performTextInput("")
    }

    fun leaveNegativePromptEmpty() {
        typeNegativePrompt("")
    }

    fun useDefaultPromptScale() {
        setPromptScale(DEFAULT_PROMPT_SCALE)
    }

    fun setPromptScale(value: Float) {
        val promptScaleSlider = testRule.activity.getString(R.string.prompt_scale)
        testRule.onNodeWithTag(promptScaleSlider).performTouchInput { swipeRight(value) }
    }

    fun useDefaultPromptStep() {
        setPromptStep(DEFAULT_PROMPT_STEP)
    }

    fun setPromptStep(value: Int) {
        val promptStepSlider = testRule.activity.getString(R.string.prompt_step)
        testRule.onNodeWithTag(promptStepSlider).performTouchInput { swipeRight(value.toFloat()) }
    }

    fun clickGenerateImage() {
        val generateImage = testRule.activity.getString(R.string.generate_image)
        testRule.onNodeWithTag(generateImage).performClick()
    }

    infix fun verify(block: PromptVerification.() -> Unit): PromptVerification {
        return PromptVerification(testRule).apply(block)
    }
}

internal class PromptVerification(private val testRule: ComponentActivityTestRule) {
    fun imageResultScreenIsDisplayed() {
        val successScreen = testRule.activity.getString(R.string.success_screen)
        testRule.onNodeWithText(successScreen).assertExists()
    }

    fun errorMessageIsDisplayed() {
        val errorMessage = testRule.activity.getString(R.string.error_message)
        testRule.onNodeWithText(errorMessage).assertExists()
    }
}