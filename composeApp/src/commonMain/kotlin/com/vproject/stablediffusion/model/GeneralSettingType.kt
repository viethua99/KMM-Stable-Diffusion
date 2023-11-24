package com.vproject.stablediffusion.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.vproject.stablediffusion.presentation.component.CustomIcons

enum class GeneralSettingType(
    val leadingIcon: ImageVector,
    val title: String,
) {
    DISPLAY_LANGUAGE(
        leadingIcon = CustomIcons.OutlinedInfo,
        title = "Display language",
    ),
    DARK_MODE(
        leadingIcon = CustomIcons.OutlinedInfo,
        title = "Dark mode",
    ),
    PRIVACY_POLICY(
        leadingIcon = CustomIcons.OutlinedInfo,
        title = "Privacy policy",
    ),
    TERM_OF_SERVICE(
        leadingIcon = CustomIcons.OutlinedInfo,
        title = "Term of service",
    ),
    ABOUT_TEXT_TO_IMAGE(
        leadingIcon = CustomIcons.OutlinedInfo,
        title = "About Text To Image",
    ),
}