package com.vproject.texttoimage.feature.generate

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vproject.texttoimage.core.designsystem.component.TextToImageFilledButton
import com.vproject.texttoimage.core.designsystem.component.TextToImageTextField
import com.vproject.texttoimage .core.designsystem.icon.TextToImageIcons
import com.vproject.texttoimage.core.model.data.FavorableStyle

private val randomList = listOf(
    "Portrait of Harry Potter cooking cheeseburger",
    "Dwayne Johnson as Superman, realistic portrait",
    "Colin Farrell as the president of the USA",
    "Happy charming googly-eyed potato walking around a cardboard diorama town chatting"
)

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
            onToggleFavoriteStyleItem = onToggleFavoriteStyleItem,
            onGenerateButtonClicked = onGenerateButtonClicked
        )
    }
}

@Composable
private fun GenerateContent(
    modifier: Modifier = Modifier,
    styleList: List<FavorableStyle>,
    onToggleFavoriteStyleItem: (styleId: String, isFavorite: Boolean) -> Unit,
    onGenerateButtonClicked: (prompt: String, selectedStyleId: String) -> Unit
) {
    var promptValue by remember { mutableStateOf("") }
    var selectedStyleId by remember { mutableStateOf("") }

    Column(modifier.padding(start = 10.dp, end = 10.dp)) {
        GeneratePromptTextField(
            onValueChange = { promptValue = it }, value = promptValue,
            modifier = Modifier.testTag(GenerateTestTags.GeneratePromptTextField)
        )
        Spacer(Modifier.height(10.dp))
        TextToImageFilledButton(
            modifier = Modifier.height(35.dp),
            onClick = {
                promptValue = randomList.filter { it != promptValue }.random()
            },
            text = { Text(text = "Surprise Me", style = TextStyle(fontSize = 12.sp)) },
            leadingIcon = {
                Icon(imageVector = TextToImageIcons.RoundedAutoFixNormal, contentDescription = null)
            },
        )

        Spacer(Modifier.height(15.dp))
        Text(
            text = "Select Style",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
        Spacer(Modifier.height(10.dp))
        SelectStyleList(
            modifier = Modifier.fillMaxWidth(),
            styleList = styleList,
            onToggleFavoriteStyleItem = onToggleFavoriteStyleItem,
            onStyleSelected = { styleId -> selectedStyleId = styleId }
        )
        Spacer(Modifier.height(10.dp))
        GenerateButton(
            onClick = { onGenerateButtonClicked(promptValue, selectedStyleId) },
            modifier = Modifier.testTag(GenerateTestTags.GenerateImageButton)
        )
    }
}

@Composable
private fun SelectStyleList(
    modifier: Modifier = Modifier,
    styleList: List<FavorableStyle>,
    onToggleFavoriteStyleItem: (styleId: String, isFavorite: Boolean) -> Unit,
    onStyleSelected: (selectedStyleId: String) -> Unit
) {
    val lazyGridState = rememberLazyGridState()
    LazyHorizontalGrid(
        modifier = modifier.height(280.dp),
        state = lazyGridState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        rows = GridCells.Fixed(2)
    ) {
        items(
            items = styleList,
            key = { it.style.id },
        ) { style ->
            StyleItem(
                style = style,
                modifier = Modifier.width(100.dp),
                onToggleFavoriteStyleItem = onToggleFavoriteStyleItem,
                onStyleSelected = onStyleSelected
            )
        }
    }
}

@Composable
private fun StyleItem(
    modifier: Modifier = Modifier,
    style: FavorableStyle,
    onToggleFavoriteStyleItem: (styleId: String, isFavorite: Boolean) -> Unit,
    onStyleSelected: (selectedStyleId: String) -> Unit
) {
    Column {
        Box(
            modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally)
                .clickable { onStyleSelected(style.style.id) }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(style.style.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium),
            )
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
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            .size(20.dp)
    ) {
        Icon(
            imageVector = if (isFavorite) TextToImageIcons.FillStarRate else TextToImageIcons.OutlinedStarRate,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .shadow(
                    elevation = 1.dp,
                    shape = CircleShape
                )
                .background(
                    color = Color.Black,
                    shape = CircleShape
                )
                .padding(4.dp)
        )
    }
}

@Composable
private fun GeneratePromptTextField(
    onValueChange: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier
) {
    TextToImageTextField(
        onValueChange = onValueChange,
        value = value,
        hint = stringResource(id = R.string.generate_main_hint),
        subHint = stringResource(id = R.string.generate_sub_hint),
        leadingIcon = {
            Icon(
                imageVector = TextToImageIcons.DefaultHistory,
                contentDescription = null,
            )
        },
        trailingIcon = {
            Icon(
                imageVector = TextToImageIcons.DefaultClose,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15))
    )
}

@Composable
private fun GenerateButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextToImageFilledButton(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        text = {
            Text(
                text = stringResource(id = R.string.generate),
                style = TextStyle(fontWeight = FontWeight.SemiBold),
            )
        }
    )
}

internal object GenerateTestTags {
    const val GenerateContent = "GenerateContent"
    const val GeneratePromptTextField = "GeneratePromptTextField"
    const val GenerateImageButton = "GenerateImageButton"
}

@Preview
@Composable
private fun GenerateScreenPreview() {
    val successGenerateUiState = GenerateUiState.Success(listOf())
    GenerateScreen(
        generateUiState = successGenerateUiState,
        modifier = Modifier.fillMaxSize(),
        onToggleFavoriteStyleItem = { _, _ -> },
        onGenerateButtonClicked = { _ , _ -> })
}

@Preview
@Composable
private fun GenerateContentPreview() {
    GenerateContent(
        styleList = listOf(),
        onToggleFavoriteStyleItem = { _, _ -> },
        onGenerateButtonClicked = { _ , _ -> })
}

@Preview
@Composable
private fun GeneratePromptTextFieldPreview() {
    GeneratePromptTextField(onValueChange = { }, value = "")
}

@Preview
@Composable
private fun GenerateImageButtonPreview() {
    GenerateButton(onClick = {})
}
