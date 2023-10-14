package com.vproject.texttoimage.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.vproject.texttoimage.core.designsystem.R
import com.vproject.texttoimage.core.designsystem.theme.LocalTintTheme

/**
 * A wrapper around [AsyncImage] which determines the colorFilter based on the theme
 */
@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val iconTint = LocalTintTheme.current.iconTint
    SubcomposeAsyncImage(
        loading = {
            Box(modifier = Modifier.size(5.dp)) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary)
            }
        },
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null,
        modifier = modifier,
    )
}