package com.vproject.stablediffusion.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Stable Diffusion navigation bar with content slot. Wraps Material 3 [NavigationBar].
 *
 * @param modifier Modifier to be applied to the navigation bar.
 * @param content Destinations inside the navigation bar. This should contain multiple
 * [NavigationBarItem]s.
 */
@Composable
fun StableDiffusionNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Column {
        Divider (
            color = Color.Gray,
            modifier = Modifier
                .height(0.4.dp)
                .fillMaxWidth()
        )
        NavigationBar(
            modifier = modifier,
            contentColor = StableDiffusionNavigationDefaults.navigationContentColor(),
            tonalElevation = 0.dp,
            content = content,
        )
    }
}

/**
 * Stable Diffusion navigation bar item with icon and label content slots. Wraps Material 3
 * [NavigationBarItem].
 *
 * @param selected Whether this item is selected.
 * @param onClick The callback to be invoked when this item is selected.
 * @param icon The item icon content.
 * @param modifier Modifier to be applied to this item.
 * @param selectedIcon The item icon content when selected.
 * @param enabled controls the enabled state of this item. When `false`, this item will not be
 * clickable and will appear disabled to accessibility services.
 * @param label The item text label content.
 * @param alwaysShowLabel Whether to always show the label for this item. If false, the label will
 * only be shown when this item is selected.
 */
@Composable
fun RowScope.StableDiffusionNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = StableDiffusionNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = StableDiffusionNavigationDefaults.navigationContentColor(),
            selectedTextColor = StableDiffusionNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = StableDiffusionNavigationDefaults.navigationContentColor(),
            indicatorColor = StableDiffusionNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

/**
 * Stable Diffusion navigation default values.
 */
object StableDiffusionNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.primary

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.surface
}