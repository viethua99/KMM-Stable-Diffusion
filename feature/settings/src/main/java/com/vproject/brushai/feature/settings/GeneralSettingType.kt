package com.vproject.brushai.feature.settings

import androidx.compose.ui.graphics.vector.ImageVector
import com.vproject.brushai.core.designsystem.icon.BrushAiIcons

enum class GeneralSettingType(
    val leadingIcon: ImageVector,
    val titleTextId: Int,
) {
    DISPLAY_LANGUAGE(
        leadingIcon = BrushAiIcons.OutlinedLanguage,
        titleTextId = R.string.display_language,
    ),
    DARK_MODE(
        leadingIcon = BrushAiIcons.OutlinedDarkMode,
        titleTextId = R.string.dark_mode,
    ),
    PRIVACY_POLICY(
        leadingIcon = BrushAiIcons.OutlinedVpnKey,
        titleTextId = R.string.privacy_policy,
    ),
    TERM_OF_SERVICE(
        leadingIcon = BrushAiIcons.OutlinedReceiptLong,
        titleTextId = R.string.term_of_service,
    ),
    ABOUT_BRUSH_AI(
        leadingIcon = BrushAiIcons.OutlinedInfo,
        titleTextId = R.string.about_brush_ai,
    ),
}