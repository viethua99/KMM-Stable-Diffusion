package com.vproject.texttoimage.feature.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vproject.texttoimage.core.designsystem.component.TextToImageFilledButton
import com.vproject.texttoimage.core.designsystem.component.TextToImageTopAppBar
import com.vproject.texttoimage.core.designsystem.component.DynamicAsyncImage
import com.vproject.texttoimage.core.designsystem.icon.TextToImageIcons

@Composable
internal fun ResultRoute(
    modifier: Modifier = Modifier, viewModel: ResultViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val resultUiState by viewModel.resultUiState.collectAsStateWithLifecycle()
    ResultScreen(
        modifier.fillMaxSize(), resultUiState,
        onBackClick = onBackClick,
        onDownloadClick = viewModel::downloadImageFromUrl
    )
}

@Composable
internal fun ResultScreen(
    modifier: Modifier = Modifier,
    resultUiState: ResultUiState,
    onBackClick: () -> Unit = {},
    onDownloadClick: (imageUrl: String) -> Unit = {}
) {
    Column(modifier = modifier) {
        ResultTopAppBar(onBackClick = onBackClick)
        if (resultUiState is ResultUiState.ShowResult) {
            Column(
                Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 80.dp)
            ) {
                Column(Modifier.weight(1f)) {
                    ResultImage(resultUiState.url)
                    ResultStyleRow(text = resultUiState.style)
                    ResultPromptRow(content = resultUiState.prompt)
                }
                ResultButtonRow(onDownloadClick = {
                    onDownloadClick(resultUiState.url)
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ResultTopAppBar(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    TextToImageTopAppBar(
        titleRes = R.string.result,
        navigationIcon = TextToImageIcons.RoundedArrowBack,
        navigationIconContentDescription = "Navigation icon",
        onNavigationClick = onBackClick
    )
}

@Composable
private fun ResultImage(imageUrl: String) {
    DynamicAsyncImage(
        modifier = Modifier
            .height(450.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium),
        imageUrl = imageUrl,
        contentDescription = null
    )
}

@Composable
private fun ResultStyleRow(
    modifier: Modifier = Modifier,
    text: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Style:",
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        )

        Text(
            text,
            textAlign = TextAlign.End,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
private fun ResultPromptRow(modifier: Modifier = Modifier, content: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Prompt:", style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            ), modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.CopyAll,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(8.dp))
    }
    Text(
        overflow = TextOverflow.Ellipsis,
        text = content,
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
    )
}

@Composable
private fun ResultButtonRow(
    modifier: Modifier = Modifier,
    onDownloadClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextToImageFilledButton(
            modifier = Modifier.weight(1f),
            enabled = false,
            text = { Text(text = "Share") }, onClick = { /*TODO*/ },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
            },
        )
        Spacer(modifier = Modifier.width(10.dp))
        TextToImageFilledButton(
            modifier = Modifier.weight(1f),
            text = { Text(text = "Download") }, onClick = {
                onDownloadClick()
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Download, contentDescription = null)
            },
        )
    }
}

internal object ResultTestTags {

}

@Preview
@Composable
private fun ResultScreenPreview() {
    ResultScreen(resultUiState = ResultUiState.Empty)
}