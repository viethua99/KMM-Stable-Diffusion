package com.vproject.texttoimage.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.vproject.texttoimage.R
import com.vproject.texttoimage.feature.gallery.R as galleryR
import com.vproject.texttoimage.core.designsystem.icon.TextToImageIcons
import com.vproject.texttoimage.feature.generate.R as generateR

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    GENERATE(
        selectedIcon = TextToImageIcons.RoundedAutoFixNormal,
        unselectedIcon = TextToImageIcons.RoundedAutoFixNormal,
        iconTextId = generateR.string.generate,
        titleTextId = R.string.app_name,
    ),
    GALLERY(
        selectedIcon = TextToImageIcons.PhotoLibrary,
        unselectedIcon = TextToImageIcons.PhotoLibrary,
        iconTextId = galleryR.string.gallery,
        titleTextId = R.string.app_name,
    )

}