package com.vproject.stablediffusion.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

/**
 * Custom Slider.
 *
 * @param modifier Modifier to be applied to the slider.
 * @param value Current value of the slider.
 * @param onValueChange Will be called when the user slide the slider.
 * @param valueRange Range of values that this slider can take.
 */
@Composable
fun CustomSlider(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Slider(
            value = value,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.onSecondary,
            ),
            onValueChange = onValueChange,
            valueRange = valueRange,
            modifier = Modifier.weight(9f)
        )
        Text(
            text = value.roundToInt().toString(),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}