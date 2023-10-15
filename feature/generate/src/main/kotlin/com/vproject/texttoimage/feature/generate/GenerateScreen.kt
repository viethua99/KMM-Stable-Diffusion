package com.vproject.texttoimage.feature.generate

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vproject.texttoimage.core.designsystem.component.DynamicAsyncImage
import com.vproject.texttoimage.core.designsystem.component.TextToImageFilledButton
import com.vproject.texttoimage.core.designsystem.component.TextToImageTextField
import com.vproject.texttoimage.core.designsystem.icon.TextToImageIcons
import com.vproject.texttoimage.core.model.data.FavorableStyle
import com.vproject.texttoimage.core.model.data.PromptData
import kotlinx.coroutines.launch

@Composable
internal fun GenerateRoute(
    modifier: Modifier = Modifier,
    viewModel: GenerateViewModel = hiltViewModel(),
    onGenerateButtonClicked: (prompt: String, selectedStyleId: String) -> Unit
) {
    val promptUiState by viewModel.generateUiState.collectAsStateWithLifecycle()
    GenerateScreen(
        modifier = modifier.fillMaxSize(),
        generateUiState = promptUiState,
        onToggleFavoriteStyleItem = viewModel::updateFavoriteStyle,
        onGenerateButtonClicked = onGenerateButtonClicked
    )
}

@Composable
internal fun GenerateScreen(
    modifier: Modifier = Modifier,
    generateUiState: GenerateUiState,
    onToggleFavoriteStyleItem: (styleId: String, isFavorite: Boolean) -> Unit,
    onGenerateButtonClicked: (prompt: String, selectedStyleId: String) -> Unit
) {
    if (generateUiState is GenerateUiState.Success) {
        GenerateContent(
            modifier.testTag(GenerateTestTags.GenerateContent),
            styleList = generateUiState.styles,
            topTrendingList = generateUiState.topTrendingList,
            onToggleFavoriteStyleItem = onToggleFavoriteStyleItem,
            onGenerateButtonClicked = onGenerateButtonClicked
        )
    }
}

@Composable
private fun GenerateContent(
    modifier: Modifier = Modifier,
    styleList: List<FavorableStyle>,
    topTrendingList: List<PromptData>,
    onToggleFavoriteStyleItem: (styleId: String, isFavorite: Boolean) -> Unit,
    onGenerateButtonClicked: (prompt: String, selectedStyleId: String) -> Unit
) {
    var promptValue by remember { mutableStateOf("") }
    var selectedStyleId by remember { mutableStateOf("1") }

    val listState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    LazyVerticalGrid(
        modifier = modifier.padding(start = 10.dp, end = 10.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        state = listState
    ) {
        item(span = { GridItemSpan(2) }) {
            Column {
                GeneratePromptTextField(
                    modifier = Modifier.testTag(GenerateTestTags.GeneratePromptTextField),
                    value = promptValue,
                    onValueChange = { promptValue = it },
                    onClearContentClick = { promptValue = ""}
                )

                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Select your style",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                )
                Spacer(Modifier.height(10.dp))
                SelectStyleList(
                    modifier = Modifier.fillMaxWidth(),
                    styleList = styleList,
                    selectedStyleId = selectedStyleId,
                    onToggleFavoriteStyleItem = onToggleFavoriteStyleItem,
                    onStyleSelected = { styleId -> selectedStyleId = styleId }
                )
                Spacer(Modifier.height(10.dp))
                GenerateButton(
                    modifier = Modifier.testTag(GenerateTestTags.GenerateImageButton),
                    enabled = promptValue.isNotEmpty() && selectedStyleId.isNotEmpty(),
                    onClick = { onGenerateButtonClicked(promptValue, selectedStyleId) }
                )

                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Top trending",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                )
                Spacer(Modifier.height(10.dp))
            }
        }

        items(topTrendingList) { promptData ->
            TopTrendingItem(
                promptData = promptData,
                onTryClick = {
                    promptValue = it
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            )
        }
    }
}

@Composable
private fun SelectStyleList(
    modifier: Modifier = Modifier,
    styleList: List<FavorableStyle>,
    selectedStyleId: String,
    onToggleFavoriteStyleItem: (styleId: String, isFavorite: Boolean) -> Unit,
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
            key = { it.style.id },
        ) { style ->
            StyleItem(
                style = style,
                modifier = Modifier.width(100.dp),
                isSelected = selectedStyleId == style.style.id,
                onToggleFavoriteStyleItem = onToggleFavoriteStyleItem,
                onStyleSelected = {
                    onStyleSelected(it)
                }
            )
        }
    }
}

@Composable
private fun StyleItem(
    modifier: Modifier = Modifier,
    style: FavorableStyle,
    isSelected: Boolean,
    onToggleFavoriteStyleItem: (styleId: String, isFavorite: Boolean) -> Unit,
    onStyleSelected: (selectedStyleId: String) -> Unit
) {
    Column {
        Box(
            modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                    RoundedCornerShape(10)
                )
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally)
                .clickable { onStyleSelected(style.style.id) }
        ) {
            DynamicAsyncImage(
                imageUrl = style.style.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium),
            )
            if (!isSelected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.7f)
                        .background(MaterialTheme.colorScheme.onSurface, RoundedCornerShape(10))
                        .clip(MaterialTheme.shapes.medium)
                )
            }
            FavoriteStyleButton(
                isFavorite = style.isFavorite,
                onClick = { onToggleFavoriteStyleItem(style.style.id, !style.isFavorite) },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 6.dp, bottom = 6.dp)
            )
        }

        Text(
            text = style.style.name,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 6.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun FavoriteStyleButton(
    isFavorite: Boolean,
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(24.dp)
    ) {
        Icon(
            imageVector = if (isFavorite) TextToImageIcons.FilledFavorite else TextToImageIcons.OutlinedFavorite,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .shadow(
                    elevation = 1.dp,
                    shape = CircleShape
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                )
                .padding(4.dp)
        )
    }
}

@Composable
private fun GeneratePromptTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearContentClick: () -> Unit

    ) {
    TextToImageTextField(
        onValueChange = onValueChange,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp),
        value = value,
        hint = stringResource(id = R.string.generate_main_hint),
        subHint = stringResource(id = R.string.generate_sub_hint),
        leadingIcon = {
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                imageVector = TextToImageIcons.DefaultHistory,
                contentDescription = null,
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onClearContentClick() },
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = TextToImageIcons.DefaultClose,
                    contentDescription = null
                )
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun GenerateButton(modifier: Modifier = Modifier, enabled: Boolean, onClick: () -> Unit) {
    TextToImageFilledButton(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        enabled = enabled,
        text = {
            Text(
                text = stringResource(id = R.string.generate),
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
            )
        }
    )
}

@Composable
private fun TopTrendingItem(
    modifier: Modifier = Modifier,
    promptData: PromptData,
    onTryClick: (promptContent: String) -> Unit = {}
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        DynamicAsyncImage(
            imageUrl = promptData.imageUrl ?: "",
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
                modifier = Modifier.weight(8f),
                text = promptData.content,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Box(
                modifier = Modifier
                    .weight(2f)
                    .height(35.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        onTryClick(promptData.content)
                    }) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    text = "Try",
                )
            }
        }
    }
}

internal object GenerateTestTags {
    const val GenerateContent = "GenerateContent"
    const val GeneratePromptTextField = "GeneratePromptTextField"
    const val GenerateImageButton = "GenerateImageButton"
}

@Preview
@Composable
private fun GenerateScreenPreview() {
    val successGenerateUiState = GenerateUiState.Success(listOf(), listOf())
    GenerateScreen(
        generateUiState = successGenerateUiState,
        modifier = Modifier.fillMaxSize(),
        onToggleFavoriteStyleItem = { _, _ -> },
        onGenerateButtonClicked = { _, _ -> })
}

@Preview
@Composable
private fun GenerateContentPreview() {
    GenerateContent(
        styleList = listOf(),
        topTrendingList = listOf(),
        onToggleFavoriteStyleItem = { _, _ -> },
        onGenerateButtonClicked = { _, _ -> })
}

@Preview
@Composable
private fun GeneratePromptTextFieldPreview() {
    GeneratePromptTextField(value = "", onValueChange = { }, onClearContentClick = {})
}

@Preview
@Composable
private fun GenerateImageButtonPreview() {
    GenerateButton(onClick = {}, enabled = true)
}
