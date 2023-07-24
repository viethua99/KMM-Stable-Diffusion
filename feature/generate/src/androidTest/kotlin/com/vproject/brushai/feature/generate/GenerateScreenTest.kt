package com.vproject.brushai.feature.generate

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class GenerateScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenLoadStyleSelectionListSucceeded_thenDisplayGenerateContent() {
        val generateUiState = GenerateUiState.Success(emptyList())
        launchGenerateScreen(composeTestRule, generateUiState) {
        } verify {
            generateContentIsDisplayed()
        }
    }

    @Test
    fun whenLoadStyleSelectionListSucceeded_thenDisplayTextFieldAndButton() {
        val generateUiState = GenerateUiState.Success(emptyList())
        launchGenerateScreen(composeTestRule, generateUiState) {
        } verify {
            generateContentIsDisplayed()
            generateTextFieldIsDisplayed()
            generateButtonIsDisplayed()
            generateButtonIsClickable()
        }
    }

    @Test
    fun whenGeneratingImageWithValidMainPrompt_thenDismissHintAndShowContent() {
        val generateUiState = GenerateUiState.Success(emptyList())
        val promptContent = "Mario is driving on a scooter"
        launchGenerateScreen(composeTestRule, generateUiState) {
            typePrompt(promptContent)
        } verify {
            generateContentIsDisplayed()
            generateButtonIsClickable()
            promptValueIsDisplayed(promptContent)
            promptHintDoesNotExist()
        }
    }

    @Test
    fun whenGeneratingImageWithValidVeryLongPrompt_thenDismissHintAndShowContent() {
        val generateUiState = GenerateUiState.Success(emptyList())
        val promptContent = "Mario is driving on a scooterMario is driving on a scooterMario is driving on a scooterMario is driving on a scooterMario is driving on a scooter"
        launchGenerateScreen(composeTestRule, generateUiState) {
            typePrompt(promptContent)
        } verify {
            generateContentIsDisplayed()
            generateButtonIsClickable()
            promptValueIsDisplayed(promptContent)
            promptHintDoesNotExist()
        }
    }

    @Test
    fun whenGeneratingImageWithValidMultipleLinesPrompt_thenDismissHintAndShowContent() {
        val generateUiState = GenerateUiState.Success(emptyList())
        val promptContent = "Mario is driving on a scooter\nMario is driving on a scooter\nMario is driving on a scooter\nMario is driving on a scooter\nMario is driving on a scooter\n"
        launchGenerateScreen(composeTestRule, generateUiState) {
            typePrompt(promptContent)
        } verify {
            generateContentIsDisplayed()
            generateButtonIsClickable()
            promptValueIsDisplayed(promptContent)
            promptHintDoesNotExist()
        }
    }

    @Test
    fun whenGeneratingImageWithEmptyMainPrompt_thenDisplayHint() {
        val generateUiState = GenerateUiState.Success(emptyList())
        launchGenerateScreen(composeTestRule, generateUiState) {
            clickGenerateButton()
        } verify {
            generateContentIsDisplayed()
            generateButtonIsDisplayed()
            generateButtonIsClickable()
            promptHintIsDisplayed()
        }
    }

    @Test
    fun whenGeneratingImageWithRemovedMainPrompt_thenDisplayHint() {
        val generateUiState = GenerateUiState.Success(emptyList())
        val promptContent = "Mario is driving on a scooter"
        launchGenerateScreen(composeTestRule, generateUiState) {
            typePrompt(promptContent)
            clearPrompt()
            clickGenerateButton()
        } verify {
            generateContentIsDisplayed()
            generateButtonIsClickable()
            promptHintIsDisplayed()
        }
    }
}