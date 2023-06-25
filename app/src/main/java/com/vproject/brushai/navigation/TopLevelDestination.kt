package com.vproject.brushai.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.vproject.brushai.R
import com.vproject.brushai.core.designsystem.icon.BrushAiIcons
import com.vproject.brushai.feature.generate.R as generateR

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
        selectedIcon = BrushAiIcons.RoundedAutoFixNormal,
        unselectedIcon = BrushAiIcons.OutlinedAutoFixNormal,
        iconTextId = generateR.string.generate,
        titleTextId = R.string.app_name,
    ),
    EXPLORE(
        selectedIcon = BrushAiIcons.RoundedLanguage,
        unselectedIcon = BrushAiIcons.OutlinedLanguage,
        iconTextId = R.string.explore,
        titleTextId = R.string.explore,
    )

}