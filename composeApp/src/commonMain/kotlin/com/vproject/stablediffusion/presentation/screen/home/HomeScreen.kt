package com.vproject.stablediffusion.presentation.screen.home

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.vproject.stablediffusion.SharedRes
import com.vproject.stablediffusion.model.ImageToImageSample
import com.vproject.stablediffusion.model.StableDiffusionMode
import com.vproject.stablediffusion.model.TextToImageSample
import com.vproject.stablediffusion.model.imageToImageSampleList
import com.vproject.stablediffusion.model.textToImageSampleList
import com.vproject.stablediffusion.presentation.component.BeforeAfterLayout
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.screen.detail.DetailScreen
import com.vproject.stablediffusion.presentation.screen.generate.GenerateScreen
import com.vproject.stablediffusion.presentation.screen.recent.RecentModel
import dev.icerock.moko.resources.compose.painterResource
import kotlin.math.absoluteValue

object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(CustomIcons.Home)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Home",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val parentNavigator = LocalNavigator.current?.parent
        val screenModel = getScreenModel<RecentModel>()
        HomeContent(
            onStableDiffusionModeClicked = { stableDiffusionMode ->
                if (stableDiffusionMode != StableDiffusionMode.AI_INPAINT) {
                    parentNavigator?.push(GenerateScreen(stableDiffusionMode))
                }
            },
            onSampleClicked = {
                parentNavigator?.push(DetailScreen("", "", ""))
            }
        )
    }
}

@Composable
private fun HomeContent(
    onStableDiffusionModeClicked: (stableDiffusionMode: StableDiffusionMode) -> Unit = {},
    onSampleClicked: () -> Unit = {},
) {
    Column {
        HomeSearch()
        Column(
            modifier = Modifier.padding(10.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            CreationSection(onStableDiffusionModeClicked = onStableDiffusionModeClicked)
            Spacer(modifier = Modifier.height(10.dp))
            TextToImageSection(onSampleClicked = onSampleClicked)
            Spacer(modifier = Modifier.height(10.dp))
            ImageToImageSection(onSampleClicked = onSampleClicked)
        }
    }
}

@Composable
private fun HomeSearch() {
    val containerColor = MaterialTheme.colorScheme.surface.copy(alpha = .2f)
    TextField(
        value = "",
        onValueChange = { },
        placeholder = { Text(text = "Popular Searches: Dragon Year") },
        leadingIcon = {
            IconButton(onClick = {}) {
                Icon(CustomIcons.Search, contentDescription = "Popular Search")
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.surface,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.surface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
        ),
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
    )
}

@Composable
private fun SectionHeader(
    title: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    isTrailingButtonEnabled: Boolean = false,
    trailingButtonTitle: String = "",
    onTrailingButtonClicked: () -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        leadingIcon?.let { nonNullLeadingIcon ->
            nonNullLeadingIcon()
        }

        val leadingIconStartPadding = if (leadingIcon != null) ButtonDefaults.IconSpacing else 0.dp
        Box(Modifier.padding(start = leadingIconStartPadding)) {
            Text(
                title,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
            )
        }

        if (isTrailingButtonEnabled) {
            Spacer(
                Modifier.weight(1f).fillMaxHeight()
            )

            Text(
                trailingButtonTitle,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                ),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CreationSection(
    onStableDiffusionModeClicked: (stableDiffusionMode: StableDiffusionMode) -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { StableDiffusionMode.entries.size })

    SectionHeader("Creation", leadingIcon = {
        Icon(
            tint = MaterialTheme.colorScheme.onSurface,
            imageVector = CustomIcons.Home,
            contentDescription = null
        )
    })
    Spacer(Modifier.height(10.dp))
    HorizontalPager(
        modifier = Modifier.height(400.dp),
        state = pagerState,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 32.dp)
    ) { index ->
        val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
        val size by animateFloatAsState(
            targetValue =  1 - pageOffset.absoluteValue * 0.1f,
            animationSpec = tween(150)
        )
        CreationModeItem(
            modifier = Modifier.width(270.dp).graphicsLayer {
                scaleX = size
                scaleY = size
            },
            stableDiffusionMode = StableDiffusionMode.entries[index],
            onItemClicked = onStableDiffusionModeClicked
        )
    }
}

@Composable
private fun CreationModeItem(
    modifier: Modifier = Modifier,
    stableDiffusionMode: StableDiffusionMode,
    onItemClicked: (stableDiffusionMode: StableDiffusionMode) -> Unit = {}
) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.DarkGray)
            .clickable {
                onItemClicked(stableDiffusionMode)
            }
    ) {
        Image(
            painterResource(stableDiffusionMode.imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
                .clip(shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(vertical = 5.dp, horizontal = 10.dp),
        ) {
            Text(
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                text = stableDiffusionMode.title,
                maxLines = 1
            )

            Text(
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                ),
                text = stableDiffusionMode.explanation,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun TextToImageSection(
    onSampleClicked: () -> Unit,
) {
    SectionHeader(StableDiffusionMode.TEXT_TO_IMAGE.title, leadingIcon = {
        Icon(
            tint = MaterialTheme.colorScheme.onSurface,
            imageVector = CustomIcons.Home,
            contentDescription = null
        )
    }, isTrailingButtonEnabled = true, trailingButtonTitle = "See more >")
    Spacer(Modifier.height(10.dp))
    TextToImageList(
        textToImageSampleList = textToImageSampleList,
        onSampleClicked = onSampleClicked,
    )
}

@Composable
private fun ImageToImageSection(
    onSampleClicked: () -> Unit,
) {
    SectionHeader(StableDiffusionMode.IMAGE_TO_IMAGE.title, leadingIcon = {
        Icon(
            tint = MaterialTheme.colorScheme.onSurface,
            imageVector = CustomIcons.Home,
            contentDescription = null
        )
    }, isTrailingButtonEnabled = true, trailingButtonTitle = "See more >")
    Spacer(Modifier.height(10.dp))
    ImageToImageList(
        imageToImageSampleList = imageToImageSampleList,
        onSampleClicked = onSampleClicked,
    )
}

@Composable
private fun TextToImageList(
    modifier: Modifier = Modifier,
    textToImageSampleList: List<TextToImageSample>,
    onSampleClicked: () -> Unit,
) {
    val lazyGridState = rememberLazyGridState()
    LazyHorizontalGrid(
        modifier = modifier.height(170.dp),
        state = lazyGridState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        rows = GridCells.Fixed(1)
    ) {
        items(
            items = textToImageSampleList,
            key = { it.id },
        ) { sample ->
            TextToImageSampleItem(
                modifier = Modifier.width(120.dp),
                textToImageSample = sample,
                onItemClicked = onSampleClicked
            )
        }
    }
}

@Composable
private fun TextToImageSampleItem(
    modifier: Modifier = Modifier,
    textToImageSample: TextToImageSample,
    onItemClicked: () -> Unit = {}
) {

    Box(
        modifier
            .fillMaxWidth()
            .height(170.dp)
            .clickable {
                onItemClicked()
            }
    ) {
        Image(
            painterResource(textToImageSample.imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.medium),
        )
    }
}

@Composable
private fun ImageToImageList(
    modifier: Modifier = Modifier,
    imageToImageSampleList: List<ImageToImageSample>,
    onSampleClicked: () -> Unit,
) {
    val lazyGridState = rememberLazyGridState()
    LazyHorizontalGrid(
        modifier = modifier.height(170.dp),
        state = lazyGridState,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        rows = GridCells.Fixed(1)
    ) {
        items(
            items = imageToImageSampleList,
            key = { it.id },
        ) { sample ->
            ImageToImageSampleItem(
                imageToImageSample = sample,
                onItemClicked = onSampleClicked
            )
        }
    }
}

@Composable
private fun ImageToImageSampleItem(
    imageToImageSample: ImageToImageSample,
    onItemClicked: () -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition("before-after")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0.05f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = CubicBezierEasing(0.9f, 0.0f, 0.1f, 1.0f)),
            repeatMode = RepeatMode.Reverse
        ),
        label = "before-after"
    )

    BeforeAfterLayout(
        modifier = Modifier.clip(RoundedCornerShape(16.dp))
            .width(120.dp).height(170.dp),
        progress = { progress },
        beforeLayout = {
            Image(
                modifier = Modifier.fillMaxSize().graphicsLayer(
                    scaleX = 1 + progress * 0.18f,
                    scaleY = 1 + progress * 0.18f
                ),
                painter = painterResource(imageToImageSample.originalImageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

        },
        afterLayout = {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(imageToImageSample.generatedImageResource),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    )
}