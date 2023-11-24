package com.vproject.stablediffusion.presentation.component

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Custom Switch.
 *
 * @param modifier Modifier to be applied to the switch.
 * @param isChecked Current value of the slider.
 * @param onCheckedChange Will be called when the user uncheck / check the switch.
 */
@Composable
fun CustomSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Switch(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = onCheckedChange
    )
}
