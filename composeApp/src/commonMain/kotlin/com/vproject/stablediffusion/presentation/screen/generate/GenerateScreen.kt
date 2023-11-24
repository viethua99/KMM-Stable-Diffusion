package com.vproject.stablediffusion.presentation.screen.generate

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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.vproject.stablediffusion.model.FavorableStyle
import com.vproject.stablediffusion.model.PromptData
import com.vproject.stablediffusion.model.PromptStatus
import com.vproject.stablediffusion.model.Style
import com.vproject.stablediffusion.presentation.component.AsyncImage
import com.vproject.stablediffusion.presentation.component.CustomFilledButton
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.CustomTextField
import kotlinx.coroutines.launch

object GenerateTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(CustomIcons.FilledFavorite)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Text To Image",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<GenerateModel>()

        GenerateContent(
            styleList = listOf(
                FavorableStyle(
                    Style(
                        "1",
                        "No Style",
                        "https://us.123rf.com/450wm/oksanaoo/oksanaoo1710/oksanaoo171000062/88555541-vector-icon-prohibiting-sign-impossible-stop-and-ban-sign-vector-black-icon-on-white-background.jpg?ver=6",
                        "",
                        ""
                    ), true
                ),
                FavorableStyle(
                    Style(
                        "2",
                        "Anime",
                        "https://cdn2.stablediffusionapi.com/generations/535d2051-e532-46ca-ad28-202c3431b14f-0.png",
                        "anime artwork, anime style, key visual, vibrant, studio anime, highly detailed",
                        "photo, deformed, black and white, realism, disfigured, low contrast"
                    ), true
                ),
                FavorableStyle(
                    Style(
                        "3",
                        "Photography",
                        "https://cdn2.stablediffusionapi.com/generations/10a983ac-ea22-4e58-bce7-388fa211cf62-0.png",
                        "cinematic photo . 35mm photograph, film, bokeh, professional, 4k, highly detailed",
                        "drawing, painting, crayon, sketch, graphite, impressionist, noisy, blurry, soft, deformed, ugly"
                    ), true
                ),
                FavorableStyle(
                    Style(
                        "4",
                        "NSFW",
                        "https://cdn.stablediffusionapi.com/generations/de5977b7-3410-4199-8d96-00c82bd78f1b-0.png",
                        "nudity content, nsfw, boobs, sex, body, thick",
                        ""
                    ), true
                ),
                FavorableStyle(
                    Style(
                        "5",
                        "Fantasy Art",
                        "https://cdn2.stablediffusionapi.com/generations/2464ede4-4538-438e-b364-9fcc858a43d4-0.png",
                        "ethereal fantasy concept art of. magnificent, celestial, ethereal, painterly, epic, majestic, magical, fantasy art, cover art, dreamy",
                        "photographic, realistic, realism, 35mm film, dslr, cropped, frame, text, deformed, glitch, noise, noisy, off-center, deformed, cross-eyed, closed eyes, bad anatomy, ugly, disfigured, sloppy, duplicate, mutated, black and white"
                    ), true
                ),
                FavorableStyle(
                    Style(
                        "6",
                        "Concept Art",
                        "https://cdn2.stablediffusionapi.com/generations/b1478590-9382-42f6-b7e4-484669020d3d-0.png",
                        "concept art. digital artwork, illustrative, painterly, matte painting, highly detailed",
                        "photo, photorealistic, realism, ugly"
                    ), true
                ),
                FavorableStyle(
                    Style(
                        "7",
                        "Isometric",
                        "https://cdn2.stablediffusionapi.com/generations/a93655ef-a71f-4777-8471-195b78bf2330-0.png",
                        "isometric style . vibrant, beautiful, crisp, detailed, ultra detailed, intricate",
                        "deformed, mutated, ugly, disfigured, blur, blurry, noise, noisy, realistic, photographic"
                    ), true
                ),
                FavorableStyle(
                    Style(
                        "8",
                        "Cyberpunk",
                        "https://cdn2.stablediffusionapi.com/generations/06324b56-9d94-40af-9ee0-04abbd86e4ba-0.png",
                        "vaporwave synthwave style . cyberpunk, neon, vibes, stunningly beautiful, crisp, detailed, sleek, ultramodern, high contrast, cinematic composition",
                        "illustration, painting, crayon, graphite, abstract, glitch, deformed, mutated, ugly, disfigured"
                    ), true
                ),
                FavorableStyle(
                    Style(
                        "9",
                        "Claymation",
                        "https://cdn2.stablediffusionapi.com/generations/29446c1a-99c1-4460-8e0d-beb101daf20e-0.png",
                        "claymation style. sculpture, clay art, centered composition, play-doh",
                        "sloppy, messy, grainy, highly detailed, ultra textured, photo, mutated"
                    ), true
                ),
                FavorableStyle(
                    Style(
                        "10",
                        "Low Poly",
                        "https://cdn2.stablediffusionapi.com/generations/0592398c-2299-45bb-a5ad-dbce62fa8547-0.png",
                        "clow-poly style. ambient occlusion, low-poly game art, polygon mesh, jagged, blocky, wireframe edges, centered composition",
                        "noisy, sloppy, messy, grainy, highly detailed, ultra textured, photo"
                    ), true
                ),
            ),
            sampleList = listOf(
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://cdn2.stablediffusionapi.com/generations/1eed8268-ba30-4c70-afd6-ab6f2e8ad72f-0.png",
                    content = "Symmetry Portrait of Storm Trooper, Star Wars, sci-fi, glowing lights!! Intricate, elegant, highly detailed, digital painting, ArtStation, concept art, smooth, sharp focus, illustration, art by Artgerm, Greg Rutkowski, Alphonse Mucha"
                ),
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://cdn2.stablediffusionapi.com/generations/55b38040-ed64-471f-bf7a-4c3a27add1bc-0.png",
                    content = "Highly detailed portrait of a sewer Spiderman, tartan hoody by Atey Ghailan, Greg Rutkowski, Greg Tocchini, James Gilleard, Joe Fenton, Kaethe Butcher, gradient red, brown, cream, and white color"
                ),
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://pub-3626123a908346a7a8be8d9295f44e26.r2.dev/generations/8c253dc9-0bb0-4dd9-adec-f0223fc5299a-0.png",
                    content = "Beautiful wide shot Tatooine landscape, Luke Skywalker watches binary sunset from moisture farm, Star Wars: A New Hope (1977), Studio Ghibli, Miyazaki, Greg Rutkowski, Alphonse Mucha, Moebius, animation, golden hour, highly detailed, HDR, vivid color, 70mm"
                ),
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://cdn2.stablediffusionapi.com/generations/18432b6d-957a-4e2a-8718-fbf44fd9bb11-0.png",
                    content = "Goddess close-up portrait skull with mohawk, ram skull, skeleton, thorax, backbone, jellyfish phoenix head, nautilus, orchid, skull, betta fish, bioluminiscent creatures, intricate artwork by Tooth Wu, WLOP, and Beeple. trending on ArtStation, Greg Rutkowski's very coherent symmetrical artwork"
                ),
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://cdn2.stablediffusionapi.com/generations/741d882e-356e-43fd-b000-d1cda4a4979c-0.png",
                    content = "An astronaut holding a beach ball, stranded on an alien island. The ocean is purple, and there are two planets in the sky. Watercolour painting, oil painting, matte painting, cinematic, concept art, HD, colourful, synthwave, Studio Ghibli, purple, astral, nightmare, beautiful, otherworldly"
                ),
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://cdn2.stablediffusionapi.com/generations/acfada66-57e2-4818-ad79-af2971d04b6d-0.png",
                    content = "Totem animal totem aztek greeble tribal style fan art ornate fantasy heartstone Ankama GTA5 cover style official Behance HD ArtStation by Jesper Ejsing, RHADS, Makoto Shinkai, Lois van Baarle, Ilya Kuvshinov, Rossdraws totem color pastel vibrant radiating a glowing aura intricate, concept art, matte"
                ),
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://cdn2.stablediffusionapi.com/generations/aa138ae8-283d-4aca-8f00-14791426a84b-0.png",
                    content = "Environment castle Nathria in World of Warcraft, gothic style fully developed castle, cinematic, raining, night time, detailed, epic, concept art, matte painting, shafts of lighting, mist, photorealistic, concept art, volumetric light, cinematic epic + rule of thirds, movie concept art, cinematic"
                ),
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://cdn2.stablediffusionapi.com/generations/85319447-1270-4b77-a929-e7220ab64888-0.png",
                    content = "Clear portrait of a superhero concept between Superman and Batman, cottagecore, background hyper detailed, character concept, full body, dynamic pose, intricate, highly detailed, digital painting, ArtStation, concept art, sharp focus, illustration, art by Artgerm, Greg Rutkowski, Alphonse Mucha"
                ),
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://cdn2.stablediffusionapi.com/generations/d0fa0410-2696-42c4-bf3a-48609ff0f2a1-0.png",
                    content = "A highly detailed matte painting of a man on a hill watching a rocket launch in the distance by Studio Ghibli, Makoto Shinkai, Artgerm, WLOP, Greg Rutkowski, volumetric lighting, Octane Render, 4K resolution, trending on ArtStation, masterpiece"
                ),
                PromptData(
                    status = PromptStatus.Success,
                    imageUrl = "https://cdn2.stablediffusionapi.com/generations/a5aae4b6-b4f2-450c-8551-4ab25ea2f292-0.png",
                    content = "Highly detailed infographic of retrofuturism cars found in Popular Mechanics magazine | vintage | intricate detail | digital art | digital painting | concept art | poster | award winning | max detail"
                )
            ),
            onGenerateButtonClicked = { _, _ -> },
            onToggleFavoriteStyleItem = { _, _ -> }
        )
    }
}

@Composable
private fun GenerateContent(
    modifier: Modifier = Modifier,
    styleList: List<FavorableStyle>,
    sampleList: List<PromptData>,
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
                    value = promptValue,
                    onValueChange = { promptValue = it },
                    onClearContentClick = { promptValue = "" }
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
                    enabled = promptValue.isNotEmpty() && selectedStyleId.isNotEmpty(),
                    onClick = { onGenerateButtonClicked(promptValue, selectedStyleId) }
                )

                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Fun Samples",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                )
                Spacer(Modifier.height(10.dp))
            }
        }

        items(sampleList) { promptData ->
            SampleItem(
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
            AsyncImage(
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
            imageVector = if (isFavorite) CustomIcons.FilledFavorite else CustomIcons.OutlinedFavorite,
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
private fun GenerateButton(modifier: Modifier = Modifier, enabled: Boolean, onClick: () -> Unit) {
    CustomFilledButton(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        enabled = enabled,
        text = {
            Text(
                text = "Generate",
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
            )
        }
    )
}

@Composable
private fun SampleItem(
    modifier: Modifier = Modifier,
    promptData: PromptData,
    onTryClick: (promptContent: String) -> Unit = {}
) {
    Box(
        modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        AsyncImage(
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