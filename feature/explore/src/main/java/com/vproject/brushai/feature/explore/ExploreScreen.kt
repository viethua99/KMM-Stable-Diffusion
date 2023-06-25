package com.vproject.brushai.feature.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
internal fun ExploreRoute(
    modifier: Modifier = Modifier) {
    ExploreScreen(modifier = modifier)
}

@Composable
internal fun ExploreScreen(modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(text = "Dummy UI")
    }
}