package com.vproject.stablediffusion.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vproject.stablediffusion.model.CanvasPreset
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun CanvasList(
    modifier: Modifier = Modifier,
    canvasList: List<CanvasPreset>,
    selectedCanvasId: String,
    onCanvasSelected: (selectedCanvasId: String) -> Unit
) {
    val lazyGridState = rememberLazyGridState()
    LazyHorizontalGrid(
        modifier = modifier.height(90.dp),
        state = lazyGridState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        rows = GridCells.Fixed(1)
    ) {
        items(
            items = canvasList,
            key = { it.id },
        ) { canvas ->
            CanvasItem(
                canvasPreset = canvas,
                modifier = Modifier.width(70.dp),
                isSelected = selectedCanvasId == canvas.id,
                onCanvasSelected = {
                    onCanvasSelected(it)
                }
            )
        }
    }
}


@Composable
private fun CanvasItem(
    modifier: Modifier = Modifier,
    canvasPreset: CanvasPreset,
    isSelected: Boolean,
    onCanvasSelected: (selectedCanvasId: String) -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .border(
                if (isSelected) 2.dp else 0.dp,
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                RoundedCornerShape(10)
            )
            .aspectRatio(1f)
            .clickable { onCanvasSelected(canvasPreset.id) }
    ) {
        val iconWidth = when (canvasPreset) {
              CanvasPreset.THREE_FOUR -> 15.dp
                CanvasPreset.ONE_ONE -> 20.dp
                CanvasPreset.NINE_SIXTEEN -> 15.dp
                CanvasPreset.FOUR_THREE -> 20.dp
                CanvasPreset.SIXTEEN_NINE -> 27.dp
        }

        val iconHeight = when (canvasPreset) {
              CanvasPreset.THREE_FOUR -> 20.dp
                CanvasPreset.ONE_ONE -> 20.dp
                CanvasPreset.NINE_SIXTEEN -> 27.dp
                CanvasPreset.FOUR_THREE -> 15.dp
                CanvasPreset.SIXTEEN_NINE -> 15.dp
        }

        Column(modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.width(iconWidth).height(iconHeight)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.onSurface,
                    RoundedCornerShape(10)
                ))
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = canvasPreset.id,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp
                ),
            )
        }
    }
}