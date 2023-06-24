package com.vproject.brushai.feature.prompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class PromptScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun prompt_whenGeneratingImageWithValidMainPrompt_displayResultImageScreen() {
        val promptUiState = PromptUiState.Loaded
        launchPromptScreen(composeTestRule, promptUiState) {
            typeMainPrompt("Mario is driving on a scooter")
            leaveNegativePromptEmpty()
            useDefaultPromptScale()
            useDefaultPromptStep()
            clickGenerateImage()
        } verify {
            imageResultScreenIsDisplayed()
        }
    }

    @Test
    fun prompt_whenGeneratingImageWithValidNegativePrompt_displayResultImageScreen() {
        val promptUiState = PromptUiState.Loaded
        launchPromptScreen(composeTestRule, promptUiState) {
            typeMainPrompt("Mario is driving on a scooter")
            typeNegativePrompt("Boring face")
            useDefaultPromptScale()
            useDefaultPromptStep()
            clickGenerateImage()
        } verify {
            imageResultScreenIsDisplayed()
        }
    }

    @Test
    fun prompt_whenGeneratingImageWithValidPromptScale_displayResultImageScreen() {
        val promptUiState = PromptUiState.Loaded
        launchPromptScreen(composeTestRule, promptUiState) {
            typeMainPrompt("Mario is driving on a scooter")
            typeNegativePrompt("Boring face")
            setPromptScale(10.5f)
            useDefaultPromptStep()
            clickGenerateImage()
        } verify {
            imageResultScreenIsDisplayed()
        }
    }

    @Test
    fun prompt_whenGeneratingImageWithValidPromptStep_displayResultImageScreen() {
        val promptUiState = PromptUiState.Loaded
        launchPromptScreen(composeTestRule, promptUiState) {
            typeMainPrompt("Mario is driving on a scooter")
            typeNegativePrompt("Boring face")
            useDefaultPromptScale()
            setPromptStep(40)
            clickGenerateImage()
        } verify {
            imageResultScreenIsDisplayed()
        }
    }

    @Test
    fun prompt_whenGeneratingImageWithEmptyMainPrompt_displayErrorMessage() {
        val promptUiState = PromptUiState.Loaded
        launchPromptScreen(composeTestRule, promptUiState) {
            typeMainPrompt("")
            leaveNegativePromptEmpty()
            useDefaultPromptScale()
            useDefaultPromptStep()
            clickGenerateImage()
        } verify {
            errorMessageIsDisplayed()
        }
    }
}