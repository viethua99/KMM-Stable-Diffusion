package com.vproject.brushai.core.model.data

/**
 * Represents the settings which the user can edit within the app.
 */
data class UserEditableSettings(
    val promptCfgScaleValue: Float,
    val promptStepValue: Float,
    val darkThemeConfig: DarkThemeConfig,
)