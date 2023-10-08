package com.vproject.texttoimage.feature.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
internal fun LoadingRoute(
    modifier: Modifier = Modifier,
    viewModel: LoadingViewModel = hiltViewModel(),
    onImageGenerated: (url: String, prompt: String, styleId: String) -> Unit = {_,_,_ ->}
) {
    val loadingUiState by viewModel.loadingUiState.collectAsStateWithLifecycle()
    LoadingScreen(
        loadingUiState = loadingUiState,
        modifier = modifier.fillMaxSize(),
        onImageGenerated = onImageGenerated
    )
}

@Composable
internal fun LoadingScreen(
    modifier: Modifier = Modifier,
    loadingUiState: LoadingUiState,
    onImageGenerated: (url: String, prompt: String, styleId: String) -> Unit = {_,_,_ ->}
) {
    if (loadingUiState is LoadingUiState.Generating) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = composition, iterations = LottieConstants.IterateForever
            )
            Text(text = "Generating...")
        }
    } else if (loadingUiState is LoadingUiState.Generated) {
        onImageGenerated(loadingUiState.url, loadingUiState.prompt, loadingUiState.styleId)
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    LoadingScreen(loadingUiState = LoadingUiState.Generating)
}