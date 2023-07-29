package com.vproject.brushai.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

/**
 * Brush AI Slider.
 *
 * @param modifier Modifier to be applied to the slider.
 * @param value Current value of the slider.
 * @param onValueChange Will be called when the user slide the slider.
 * @param valueRange Range of values that this slider can take.
 */
@Composable
fun BrushAiSlider(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            modifier = Modifier.weight(9f)
        )
        Text(
            text = value.roundToInt().toString(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun BrushAiSliderPreview() {
    BrushAiSlider(
        value = 1f,
        onValueChange = {},
    )
}