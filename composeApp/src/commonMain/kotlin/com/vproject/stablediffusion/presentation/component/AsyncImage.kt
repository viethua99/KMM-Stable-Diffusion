package com.vproject.stablediffusion.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

/**
 * A wrapper around [AsyncImage] which determines the colorFilter based on the theme
 */
@Composable
fun AsyncImage(
    imageUrl: String,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    modifier: Modifier = Modifier
) {
    KamelImage(
        onLoading = {
            Box(modifier = Modifier.size(25.dp)) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary)
            }
        },
        resource = asyncPainterResource(data = imageUrl),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        colorFilter = colorFilter,
        modifier = modifier,
    )
}