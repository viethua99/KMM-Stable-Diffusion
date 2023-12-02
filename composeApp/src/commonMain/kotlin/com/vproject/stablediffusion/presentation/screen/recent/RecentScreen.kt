package com.vproject.stablediffusion.presentation.screen.recent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.vproject.stablediffusion.model.PromptData
import com.vproject.stablediffusion.model.PromptStatus
import com.vproject.stablediffusion.presentation.component.AsyncImage
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.StableDiffusionTopBar

object RecentTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(CustomIcons.Recent)

            return remember {
                TabOptions(
                    index = 1u,
                    title = "Recent",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<RecentModel>()

        RecentContent()
    }
}

@Composable
private fun RecentContent() {
    val test =  listOf(
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
    )

    Column {
        RecentTopBar(onBackClick = {})
        LazyVerticalGrid(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(test) { promptData ->
                GeneratedPromptItem(
                    promptData = promptData,
                    onItemClick = {_,_ ->}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecentTopBar(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    StableDiffusionTopBar(
        modifier = modifier,
        title = "Recent",
        navigationIcon = CustomIcons.Home,
        navigationIconContentDescription = "Navigation icon",
        onNavigationClick = onBackClick
    )
}

@Composable
private fun GeneratedPromptItem(
    modifier: Modifier = Modifier,
    promptData: PromptData,
    onItemClick: (promptContent: String, imageUrl: String) -> Unit
) {
    Column (
        modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable {
                onItemClick(promptData.content, promptData.imageUrl ?: "")
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            imageUrl = promptData.imageUrl ?: "",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
                .clip(MaterialTheme.shapes.medium),
        )

        Text(
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ),
            modifier = Modifier.fillMaxWidth(),
            text = promptData.content,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
