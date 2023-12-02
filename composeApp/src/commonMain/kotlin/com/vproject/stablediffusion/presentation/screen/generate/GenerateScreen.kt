package com.vproject.stablediffusion.presentation.screen.generate

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.vproject.stablediffusion.model.StableDiffusionMode
import com.vproject.stablediffusion.model.Style
import com.vproject.stablediffusion.presentation.component.AsyncImage
import com.vproject.stablediffusion.presentation.component.CustomFilledButton
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.CustomTextField

data class GenerateScreen(val stableDiffusionMode: StableDiffusionMode) : Screen {
    @Composable
    override fun Content() {
        val screenModel: GenerateModel = getScreenModel()

        GenerateContent()
    }
}

@Composable
private fun GenerateContent() {
    val styleList = listOf(
        Style(
            "2",
            "Anime",
            "https://cdn2.stablediffusionapi.com/generations/535d2051-e532-46ca-ad28-202c3431b14f-0.png",
            "anime artwork, anime style, key visual, vibrant, studio anime, highly detailed",
            "photo, deformed, black and white, realism, disfigured, low contrast"
        ),
        Style(
            "3",
            "Photography",
            "https://cdn2.stablediffusionapi.com/generations/10a983ac-ea22-4e58-bce7-388fa211cf62-0.png",
            "cinematic photo . 35mm photograph, film, bokeh, professional, 4k, highly detailed",
            "drawing, painting, crayon, sketch, graphite, impressionist, noisy, blurry, soft, deformed, ugly"
        ),
        Style(
            "4",
            "NSFW",
            "https://cdn.stablediffusionapi.com/generations/de5977b7-3410-4199-8d96-00c82bd78f1b-0.png",
            "nudity content, nsfw, boobs, sex, body, thick",
            ""
        ),
        Style(
            "5",
            "Fantasy Art",
            "https://cdn2.stablediffusionapi.com/generations/2464ede4-4538-438e-b364-9fcc858a43d4-0.png",
            "ethereal fantasy concept art of. magnificent, celestial, ethereal, painterly, epic, majestic, magical, fantasy art, cover art, dreamy",
            "photographic, realistic, realism, 35mm film, dslr, cropped, frame, text, deformed, glitch, noise, noisy, off-center, deformed, cross-eyed, closed eyes, bad anatomy, ugly, disfigured, sloppy, duplicate, mutated, black and white"
        ),
        Style(
            "6",
            "Concept Art",
            "https://cdn2.stablediffusionapi.com/generations/b1478590-9382-42f6-b7e4-484669020d3d-0.png",
            "concept art. digital artwork, illustrative, painterly, matte painting, highly detailed",
            "photo, photorealistic, realism, ugly"
        ),
        Style(
            "7",
            "Isometric",
            "https://cdn2.stablediffusionapi.com/generations/a93655ef-a71f-4777-8471-195b78bf2330-0.png",
            "isometric style . vibrant, beautiful, crisp, detailed, ultra detailed, intricate",
            "deformed, mutated, ugly, disfigured, blur, blurry, noise, noisy, realistic, photographic"
        ),
        Style(
            "8",
            "Cyberpunk",
            "https://cdn2.stablediffusionapi.com/generations/06324b56-9d94-40af-9ee0-04abbd86e4ba-0.png",
            "vaporwave synthwave style . cyberpunk, neon, vibes, stunningly beautiful, crisp, detailed, sleek, ultramodern, high contrast, cinematic composition",
            "illustration, painting, crayon, graphite, abstract, glitch, deformed, mutated, ugly, disfigured"
        ),
        Style(
            "9",
            "Claymation",
            "https://cdn2.stablediffusionapi.com/generations/29446c1a-99c1-4460-8e0d-beb101daf20e-0.png",
            "claymation style. sculpture, clay art, centered composition, play-doh",
            "sloppy, messy, grainy, highly detailed, ultra textured, photo, mutated"
        ),
        Style(
            "10",
            "Low Poly",
            "https://cdn2.stablediffusionapi.com/generations/0592398c-2299-45bb-a5ad-dbce62fa8547-0.png",
            "clow-poly style. ambient occlusion, low-poly game art, polygon mesh, jagged, blocky, wireframe edges, centered composition",
            "noisy, sloppy, messy, grainy, highly detailed, ultra textured, photo"
        ),
    )

    var promptValue by remember { mutableStateOf("") }
    var selectedStyleId by remember { mutableStateOf("1") }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.padding(horizontal = 10.dp, vertical = 10.dp).verticalScroll(
                rememberScrollState()
            )
        ) {
            SectionHeader(
                "Enter Prompts",
                leadingIcon = {
                    Icon(
                        tint = MaterialTheme.colorScheme.onSurface,
                        imageVector = CustomIcons.Home,
                        contentDescription = null
                    )
                })
            Spacer(Modifier.height(10.dp))
            GeneratePromptTextField(
                value = promptValue,
                onValueChange = { promptValue = it },
                onClearContentClick = { promptValue = "" }
            )

            Spacer(Modifier.height(10.dp))
            SectionHeader(
                "Choose Style",
                leadingIcon = {
                    Icon(
                        tint = MaterialTheme.colorScheme.onSurface,
                        imageVector = CustomIcons.Home,
                        contentDescription = null
                    )
                })
            Spacer(Modifier.height(10.dp))
            StyleList(
                modifier = Modifier.fillMaxWidth(),
                styleList = styleList,
                selectedStyleId = selectedStyleId,
                onStyleSelected = { styleId -> selectedStyleId = styleId }
            )
            Spacer(Modifier.height(10.dp))
            SectionHeader(
                "Choose Canvas",
                leadingIcon = {
                    Icon(
                        tint = MaterialTheme.colorScheme.onSurface,
                        imageVector = CustomIcons.Home,
                        contentDescription = null
                    )
                })
            Spacer(Modifier.height(10.dp))
            CanvasList(
                modifier = Modifier.fillMaxWidth(),
                styleList = styleList,
                selectedStyleId = selectedStyleId,
                onStyleSelected = { styleId -> selectedStyleId = styleId }
            )
        }

        DrawingButton(
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(start = 30.dp, end = 30.dp, bottom = 40.dp),
            enabled = promptValue.isNotEmpty() && selectedStyleId.isNotEmpty(),
            onClick = { }
        )
    }
}

@Composable
private fun StyleList(
    modifier: Modifier = Modifier,
    styleList: List<Style>,
    selectedStyleId: String,
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
            key = { it.id },
        ) { style ->
            StyleItem(
                style = style,
                modifier = Modifier.width(110.dp),
                isSelected = selectedStyleId == style.id,
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
    style: Style,
    isSelected: Boolean,
    onStyleSelected: (selectedStyleId: String) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier
                .fillMaxWidth()
                .border(
                    if (isSelected) 2.dp else 0.dp,
                    if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                    RoundedCornerShape(10)
                )
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally)
                .clickable { onStyleSelected(style.id) }
        ) {
            AsyncImage(
                imageUrl = style.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium),
            )
        }

        Text(
            text = style.name,
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
private fun CanvasList(
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
            StyleItem(
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
private fun GeneratePromptTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearContentClick: () -> Unit

) {
    CustomTextField(
        onValueChange = onValueChange,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 16.sp),
        value = value,
        hint = "Describe your thoughts",
        subHint = "Any detail of your image",
        leadingIcon = {
            Icon(
                tint = MaterialTheme.colorScheme.primary,
                imageVector = CustomIcons.DefaultHistory,
                contentDescription = null,
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onClearContentClick() },
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = CustomIcons.DefaultClose,
                    contentDescription = null
                )
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun DrawingButton(modifier: Modifier = Modifier, enabled: Boolean, onClick: () -> Unit) {
    CustomFilledButton(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        enabled = enabled,
        text = {
            Text(
                text = "Start Drawing",
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
            )
        }
    )
}

@Composable
private fun SectionHeader(
    title: String,
    leadingIcon: @Composable (() -> Unit)? = null
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
                    fontSize = 18.sp
                ),
            )
        }
    }
}