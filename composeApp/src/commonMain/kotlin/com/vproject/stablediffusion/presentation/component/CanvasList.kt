package com.vproject.stablediffusion.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.vproject.stablediffusion.model.Style
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun CanvasList(
    modifier: Modifier = Modifier,
    styleList: List<Style>,
    selectedStyleId: String,
    onStyleSelected: (selectedStyleId: String) -> Unit
) {
    val lazyGridState = rememberLazyGridState()
    LazyHorizontalGrid(
        modifier = modifier.height(140.dp),
        state = lazyGridState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        rows = GridCells.Fixed(1)
    ) {
        items(
            items = styleList,
            key = { it.id },
        ) { style ->
            CanvasItem(
                style = style,
                modifier = Modifier.width(80.dp),
                isSelected = selectedStyleId == style.id,
                onStyleSelected = {
                    onStyleSelected(it)
                }
            )
        }
    }
}


@Composable
private fun CanvasItem(
    modifier: Modifier = Modifier,
    style: Style,
    isSelected: Boolean,
    onStyleSelected: (selectedStyleId: String) -> Unit
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
            .clickable { onStyleSelected(style.id) }
    ) {
        Image(
            painterResource(style.imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
        )
    }
}