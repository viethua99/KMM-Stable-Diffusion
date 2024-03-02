package com.vproject.stablediffusion.presentation.component.beforeafter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.vproject.stablediffusion.SharedRes
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

/**
 * Default overlay for [BeforeAfterImage] that draws line and
 * thumb with properties provided.
 *
 * @param width of the [BeforeAfterImage]. You should get width from
 * scope of these Composables and pass to calculate bounds correctly
 * @param height of the [BeforeAfterImage]. You should get height from
 * scope of these Composables and pass to calculate bounds correctly
 * @param position current position or progress of before/after
 */
@Composable
internal fun DefaultOverlay(
    width: Dp,
    height: Dp,
    position: Offset,
    overlayStyle: OverlayStyle
) {

    val verticalThumbMove = overlayStyle.verticalThumbMove
    val dividerColor = overlayStyle.dividerColor
    val dividerWidth = overlayStyle.dividerWidth
    val thumbBackgroundColor = overlayStyle.thumbBackgroundColor
    val thumbTintColor = overlayStyle.thumbTintColor
    val thumbShape = overlayStyle.thumbShape
    val thumbElevation = overlayStyle.thumbElevation
    val thumbResource = overlayStyle.thumbResource
    val thumbSize = overlayStyle.thumbSize
    val thumbPositionPercent = overlayStyle.thumbPositionPercent


    var thumbPosX = position.x
    var thumbPosY = position.y

    val linePosition: Float

    val density = LocalDensity.current

    with(density) {
        val thumbRadius = (thumbSize / 2).toPx()
        val imageWidthInPx = width.toPx()
        val imageHeightInPx = height.toPx()

        val horizontalOffset = imageWidthInPx / 2
        val verticalOffset = imageHeightInPx / 2

        linePosition = thumbPosX.coerceIn(0f, imageWidthInPx)
        thumbPosX -= horizontalOffset

        thumbPosY = if (verticalThumbMove) {
            (thumbPosY - verticalOffset)
                .coerceIn(
                    -verticalOffset + thumbRadius,
                    verticalOffset - thumbRadius
                )
        } else {
            ((imageHeightInPx * thumbPositionPercent / 100f - thumbRadius) - verticalOffset)
        }
    }

    Box(
        modifier = Modifier.size(width, height),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            drawLine(
                dividerColor,
                strokeWidth = dividerWidth.toPx(),
                start = Offset(linePosition, 0f),
                end = Offset(linePosition, size.height)
            )
        }

        Icon(
            painter = painterResource(thumbResource),
            contentDescription = null,
            tint = thumbTintColor,
            modifier = Modifier
                .offset {
                    IntOffset(thumbPosX.toInt(), thumbPosY.toInt())
                }
                .rotate(90f)
                .shadow(thumbElevation, thumbShape)
                .background(thumbBackgroundColor)
                .size(thumbSize)
                .padding(4.dp)
        )
    }
}

/**
 * Values for styling [DefaultOverlay]
 *  @param verticalThumbMove when true thumb can move vertically based on user touch
 * @param dividerColor color if divider line
 * @param dividerWidth width if divider line
 * @param thumbBackgroundColor background color of thumb [Icon]
 * @param thumbTintColor tint color of thumb [Icon]
 * @param thumbShape shape of thumb [Icon]
 * @param thumbElevation elevation of thumb [Icon]
 * @param thumbResource drawable resource that should be used with thumb
 * @param thumbSize size of the thumb in dp
 * @param thumbPositionPercent vertical position of thumb if [verticalThumbMove] is false
 * It's between [0f-100f] to set thumb's vertical position in layout
 */
@Immutable
class OverlayStyle(
    val dividerColor: Color = Color.White,
    val dividerWidth: Dp = 1.5.dp,
    val verticalThumbMove: Boolean = false,
    val thumbBackgroundColor: Color = Color.White,
    val thumbTintColor: Color = Color.Gray,
    val thumbShape: Shape = RoundedCornerShape(6.dp),
    val thumbElevation: Dp = 2.dp,
    val thumbResource: ImageResource = SharedRes.images.ic_bidirectional_arrow,
    val thumbSize: Dp = 18.dp,
    val thumbPositionPercent: Float = 50f,
)