package com.vproject.stablediffusion.presentation.screen.generate.tab

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vproject.stablediffusion.SharedRes
import com.vproject.stablediffusion.model.Style
import com.vproject.stablediffusion.presentation.component.CanvasList
import com.vproject.stablediffusion.presentation.component.CustomFilledButton
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.CustomTextField
import com.vproject.stablediffusion.presentation.component.StepSectionHeader
import com.vproject.stablediffusion.presentation.component.StyleList

@Composable
fun TextToImageTab(
    onDrawingClicked: () -> Unit = {}
) {
    val styleList = listOf(
        Style(
            "2",
            "Anime", SharedRes.images.img_style_anime,
            "anime artwork, anime style, key visual, vibrant, studio anime, highly detailed",
            "photo, deformed, black and white, realism, disfigured, low contrast"
        ),
        Style(
            "3",
            "Photography",
            SharedRes.images.img_style_photography,
            "cinematic photo . 35mm photograph, film, bokeh, professional, 4k, highly detailed",
            "drawing, painting, crayon, sketch, graphite, impressionist, noisy, blurry, soft, deformed, ugly"
        ),
        Style(
            "4",
            "NSFW",
            SharedRes.images.img_style_nsfw,
            "nudity content, nsfw, boobs, sex, body, thick",
            ""
        ),
        Style(
            "5",
            "Fantasy Art",
            SharedRes.images.img_style_fantasy_art,
            "ethereal fantasy concept art of. magnificent, celestial, ethereal, painterly, epic, majestic, magical, fantasy art, cover art, dreamy",
            "photographic, realistic, realism, 35mm film, dslr, cropped, frame, text, deformed, glitch, noise, noisy, off-center, deformed, cross-eyed, closed eyes, bad anatomy, ugly, disfigured, sloppy, duplicate, mutated, black and white"
        ),
        Style(
            "6",
            "Concept Art",
            SharedRes.images.img_style_concept_art,
            "concept art. digital artwork, illustrative, painterly, matte painting, highly detailed",
            "photo, photorealistic, realism, ugly"
        ),
        Style(
            "7",
            "Isometric",
            SharedRes.images.img_style_isometric,
            "isometric style . vibrant, beautiful, crisp, detailed, ultra detailed, intricate",
            "deformed, mutated, ugly, disfigured, blur, blurry, noise, noisy, realistic, photographic"
        ),
        Style(
            "8",
            "Cyberpunk",
            SharedRes.images.img_style_cyberpunk,
            "vaporwave synthwave style . cyberpunk, neon, vibes, stunningly beautiful, crisp, detailed, sleek, ultramodern, high contrast, cinematic composition",
            "illustration, painting, crayon, graphite, abstract, glitch, deformed, mutated, ugly, disfigured"
        ),
        Style(
            "9",
            "Claymation",
            SharedRes.images.img_style_claymation,
            "claymation style. sculpture, clay art, centered composition, play-doh",
            "sloppy, messy, grainy, highly detailed, ultra textured, photo, mutated"
        ),
        Style(
            "10",
            "Low Poly",
            SharedRes.images.img_style_low_poly,
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
            StepSectionHeader("Enter Prompts", 1)
            Spacer(Modifier.height(10.dp))
            UploadImageCard(
                value = promptValue,
                onValueChange = { promptValue = it },
                onClearContentClick = { promptValue = "" }
            )

            Spacer(Modifier.height(10.dp))
            StepSectionHeader("Choose Style", 2)
            Spacer(Modifier.height(10.dp))
            StyleList(
                modifier = Modifier.fillMaxWidth(),
                styleList = styleList,
                selectedStyleId = selectedStyleId,
                onStyleSelected = { styleId -> selectedStyleId = styleId }
            )
            Spacer(Modifier.height(10.dp))
            StepSectionHeader("Choose Canvas", 3)
            Spacer(Modifier.height(10.dp))
            CanvasList(
                modifier = Modifier.fillMaxWidth(),
                styleList = styleList,
                selectedStyleId = selectedStyleId,
                onStyleSelected = { styleId -> selectedStyleId = styleId }
            )
            Spacer(Modifier.height(80.dp))
        }

        DrawingButton(
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(start = 30.dp, end = 30.dp, bottom = 40.dp),
            enabled = promptValue.isNotEmpty() && selectedStyleId.isNotEmpty(),
            onClick = { onDrawingClicked() }
        )
    }
}

@Composable
private fun UploadImageCard(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearContentClick: () -> Unit

) {
    CustomTextField(
        onValueChange = onValueChange,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 14.sp),
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