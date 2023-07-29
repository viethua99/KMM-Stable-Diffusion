package com.vproject.brushai.core.model.data

/**
 * Class summarizing user data
 */
data class UserData(
    val favoriteStyleIds: Set<String>,
    val promptCfgScaleValue: Float,
    val promptStepValue: Float,
    val darkThemeConfig: DarkThemeConfig,
)