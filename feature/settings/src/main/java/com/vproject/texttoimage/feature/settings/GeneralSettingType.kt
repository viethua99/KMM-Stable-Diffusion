package com.vproject.texttoimage.feature.settings

import androidx.compose.ui.graphics.vector.ImageVector
import com.vproject.texttoimage.core.designsystem.icon.TextToImageIcons

enum class GeneralSettingType(
    val leadingIcon: ImageVector,
    val titleTextId: Int,
) {
    DISPLAY_LANGUAGE(
        leadingIcon = TextToImageIcons.OutlinedLanguage,
        titleTextId = R.string.display_language,
    ),
    DARK_MODE(
        leadingIcon = TextToImageIcons.OutlinedDarkMode,
        titleTextId = R.string.dark_mode,
    ),
    PRIVACY_POLICY(
        leadingIcon = TextToImageIcons.OutlinedVpnKey,
        titleTextId = R.string.privacy_policy,
    ),
    TERM_OF_SERVICE(
        leadingIcon = TextToImageIcons.OutlinedReceiptLong,
        titleTextId = R.string.term_of_service,
    ),
    ABOUT_TEXT_TO_IMAGE(
        leadingIcon = TextToImageIcons.OutlinedInfo,
        titleTextId = R.string.about_text_to_image,
    ),
}