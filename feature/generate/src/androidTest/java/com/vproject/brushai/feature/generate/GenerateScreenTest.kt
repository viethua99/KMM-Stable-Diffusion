package com.vproject.brushai.feature.generate

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class GenerateScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun prompt_whenGeneratingImageWithValidMainPrompt_displayResultImageScreen() {
        val generateUiState = GenerateUiState.Loaded
        launchGenerateScreen(composeTestRule, generateUiState) {
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
        val generateUiState = GenerateUiState.Loaded
        launchGenerateScreen(composeTestRule, generateUiState) {
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
        val generateUiState = GenerateUiState.Loaded
        launchGenerateScreen(composeTestRule, generateUiState) {
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
        val generateUiState = GenerateUiState.Loaded
        launchGenerateScreen(composeTestRule, generateUiState) {
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
        val generateUiState = GenerateUiState.Loaded
        launchGenerateScreen(composeTestRule, generateUiState) {
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