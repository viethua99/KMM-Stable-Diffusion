package com.vproject.brushai.feature.prompt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
internal fun PromptRoute(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel = hiltViewModel()
) {
    val promptUiState by viewModel.uiState.collectAsStateWithLifecycle()
    PromptScreen(
        promptUiState = promptUiState,
        modifier = modifier
    )
}

@Composable
internal fun PromptScreen(
    promptUiState: PromptUiState,
    modifier: Modifier = Modifier,
) {
    when (promptUiState) {
        is PromptUiState.Loading -> {
            PromptLoadingWheel(modifier)
        }

        is PromptUiState.Loaded -> {
            PromptBody()
        }
    }
}

@Composable
internal fun PromptLoadingWheel(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
    }
}

@Composable
internal fun PromptBody() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
//        AsyncImage(
//            modifier = Modifier
//                .weight(6f),
//            contentDescription = "",
//            contentScale = ContentScale.Fit,
//            model = "https://cdn.stablediffusionapi.com/generations/66e740df-031e-4ed7-a145-2dd90291d1e8-0.png",
//        )

        PromptEditor(Modifier.weight(4f))
    }
}

@Composable
internal fun PromptEditor(modifier: Modifier = Modifier) {
    var mainPromptValue by remember { mutableStateOf("Mario on the scooter") }
    var negativePromptValue by remember { mutableStateOf("Mario on the scooter") }
    var promptScale by remember { mutableStateOf(10f) }
    var promptStep by remember { mutableStateOf(5f) }

    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = R.string.main_prompt_hint))
            Spacer(modifier = Modifier.size(10.dp))
            TextField(value = mainPromptValue, onValueChange = { mainPromptValue = it })
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.negative_prompt_hint))
            Spacer(modifier = Modifier.size(10.dp))
            TextField(value = negativePromptValue, onValueChange = { negativePromptValue = it })
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.prompt_scale))
            Spacer(modifier = Modifier.size(10.dp))
            Slider(value = promptScale, onValueChange = { promptScale = it })
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(id = R.string.prompt_step))
            Spacer(modifier = Modifier.size(10.dp))
            Slider(value = promptStep, onValueChange = { promptStep = it })
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color.Blue)
        ){
            Text( text = stringResource(id = R.string.generate_image) )
        }
    }
}
