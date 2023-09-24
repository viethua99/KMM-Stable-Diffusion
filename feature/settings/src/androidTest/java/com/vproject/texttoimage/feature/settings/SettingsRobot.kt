package com.vproject.texttoimage.feature.settings

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsToggleable
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

internal typealias ComponentActivityTestRule = AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>

internal fun launchSettingsScreen(
    testRule: ComponentActivityTestRule,
    uiState: SettingsUiState,
    block: SettingsRobot.() -> Unit
): SettingsRobot {
    testRule.setContent {
        SettingsScreen(settingsUiState = uiState,
            onBackClick = {},
            onPromptCfgScaleValueChange = {},
            onPromptStepValueChange = {},
            onChangeDarkThemeConfig = {})
    }
    return SettingsRobot(testRule).apply(block)
}

internal class SettingsRobot(private val testRule: ComponentActivityTestRule) {
    infix fun verify(block: SettingsVerification.() -> Unit): SettingsVerification {
        return SettingsVerification(testRule).apply(block)
    }
}

internal class SettingsVerification(private val testRule: ComponentActivityTestRule) {
    fun settingTopAppBarIsDisplayed() {
        testRule.onNodeWithTag(SettingsTestTags.SettingTopAppBar).assertIsDisplayed()
    }

    fun settingContentIsDisplayed() {
        testRule.onNodeWithTag(SettingsTestTags.SettingContent).assertIsDisplayed()
    }

    fun advancedPromptOptionCardIsDisplayed() {
        val advancedPromptOptionSectionTitle = testRule.activity.getString(R.string.advanced_prompt_option)
        testRule.onNodeWithText(advancedPromptOptionSectionTitle).assertIsDisplayed()

        val cfgScaleSectionTitle = testRule.activity.getString(R.string.cfg_scale)
        testRule.onNodeWithText(cfgScaleSectionTitle).assertIsDisplayed()

        val cfgScaleExplanation = testRule.activity.getString(R.string.cfg_scale_explanation)
        testRule.onNodeWithText(cfgScaleExplanation).assertIsDisplayed()

        val stepSectionTitle = testRule.activity.getString(R.string.steps)
        testRule.onNodeWithText(stepSectionTitle).assertIsDisplayed()

        val stepExplanation = testRule.activity.getString(R.string.steps_explanation)
        testRule.onNodeWithText(stepExplanation).assertIsDisplayed()
    }

    fun generalSettingCardIsDisplayed() {
        val generalSettingSectionTitle = testRule.activity.getString(R.string.general)
        testRule.onNodeWithText(generalSettingSectionTitle).assertIsDisplayed()

        val displayLanguageTitle = testRule.activity.getString(R.string.display_language)
        testRule.onNodeWithText(displayLanguageTitle).assertIsDisplayed()

        val darkModeTitle = testRule.activity.getString(R.string.dark_mode)
        testRule.onNodeWithText(darkModeTitle).assertIsDisplayed()

        val privacyPolicyTitle = testRule.activity.getString(R.string.privacy_policy)
        testRule.onNodeWithText(privacyPolicyTitle).assertIsDisplayed()

        val termOfServiceTitle = testRule.activity.getString(R.string.term_of_service)
        testRule.onNodeWithText(termOfServiceTitle).assertIsDisplayed()

        val aboutTextToImageTitle = testRule.activity.getString(R.string.about_text_to_image)
        testRule.onNodeWithText(aboutTextToImageTitle).assertIsDisplayed()
    }

    fun darkModeSwitchIsTurnedOff() {
        testRule.onNodeWithTag(SettingsTestTags.DarkModeSwitch).assertIsToggleable()
        testRule.onNodeWithTag(SettingsTestTags.DarkModeSwitch).assertIsOff()
    }

    fun darkModeSwitchIsTurnedOn() {
        testRule.onNodeWithTag(SettingsTestTags.DarkModeSwitch).assertIsToggleable()
        testRule.onNodeWithTag(SettingsTestTags.DarkModeSwitch).assertIsOn()
    }
}