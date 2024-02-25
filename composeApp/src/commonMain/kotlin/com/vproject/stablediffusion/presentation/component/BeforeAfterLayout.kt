package com.vproject.stablediffusion.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun BeforeAfterLayout(
    modifier: Modifier = Modifier,
    progress: () -> Float,
    beforeLayout: @Composable BoxScope.() -> Unit,
    afterLayout: @Composable BoxScope.() -> Unit
) {
    Box(modifier) {
        afterLayout()
        Box(
            modifier = Modifier
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        color = Color.Transparent,
                        size = Size(size.width * progress(), size.height),
                        blendMode = BlendMode.Clear
                    )
                    drawLine(
                        color = Color.White,
                        start = Offset(size.width * progress(), 0f),
                        end = Offset(size.width * progress(), size.height),
                        strokeWidth = 4f
                    )
                }
        ) {
            beforeLayout()
        }
    }
}