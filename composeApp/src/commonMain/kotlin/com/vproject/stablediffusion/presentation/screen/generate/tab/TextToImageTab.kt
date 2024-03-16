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
import com.vproject.stablediffusion.model.CanvasPreset
import com.vproject.stablediffusion.model.StableDiffusionMode
import com.vproject.stablediffusion.model.StylePreset
import com.vproject.stablediffusion.model.TextToImageSample
import com.vproject.stablediffusion.presentation.component.CanvasList
import com.vproject.stablediffusion.presentation.component.CustomFilledButton
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.CustomTextField
import com.vproject.stablediffusion.presentation.component.StepSectionHeader
import com.vproject.stablediffusion.presentation.component.StyleList
import dev.icerock.moko.resources.compose.painterResource

private val randomPrompt = listOf(
    "Dragon New Year poster, highest quality, lanterns, fireworks, lucky red envelope, flying, sky",
    "Dog, <Masterpiece*Golden Ratio*Sunlight*Spring>",
    "a bear is fishing, cute, river, forest, flowers, tree, cloud, sun",
    "(fluorescent colors:1.4), (translucent:1.4), (retro filters:1.4), (fantasy:1.4), candy world disney land ethereal soft fluffy soft landscape forest snowavatar pastel pink sky green blue sparkle ethereal light pastel whimsical light rainbow stars diamonds sparkle gemstone background hyper realistic ultra quality cinematic lighting immense detail full hd painting well lit, diagonal bangs, collared dress, on side masterpiece, best quality, realskin, (portrait:1.5), 1girl, blunt bangs, long hair",
    "1girl, extremely detailed, 8k wallpaper, rabbit ear, a background of extreme detail, (red eyes (extreme detail))",
    "English Medieval Witch, <Unique Nose*Blue Eyes*Sitting On A Throne*Elegant*Real Light>, beautiful",
    "masterpiece, best quality, floating city, clouds, sun",
    "Masterpiece, high quality, best quality, 4k, ultra detailed, realistic, 1girl, upper body, white swimsuit, (light pink hair), flipped hair, pink eyes, summer, beautiful and detailed face, sun, ray chasing, reflection light",
    "1 boy, male focus, solo, realistic, shirt, looking at viewer, black hair, blurry, tree, black shirt, blurry background, collared shirt, white shirt, portrait, outdoors, black eyes, upper body, short hair, closed mouth, smile, open clothes, brown eyes",
    "bustling street market,New Year's celebration,vibrant stalls,traditional decorations,delicacies,festive attire,red lantern,colorful banners,streamers",
)
@Composable
fun TextToImageTab(
    onTextToImageDrawClicked: (prompt: String, styleId: String, canvasId: String) -> Unit = { _, _, _ -> },
) {
    var promptValue by remember { mutableStateOf("") }
    var selectedStyleId by remember { mutableStateOf(StylePreset.entries[0].id) }
    var selectedCanvasId by remember { mutableStateOf(CanvasPreset.entries[0].id) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            Modifier.padding(horizontal = 12.dp, vertical = 10.dp).verticalScroll(
                rememberScrollState()
            )
        ) {
            StepSectionHeader("Enter Prompts", 1)
            Spacer(Modifier.height(10.dp))
            EnterPromptCard(
                value = promptValue,
                onValueChange = { promptValue = it },
                onRandomContentClick = {
                    promptValue = randomPrompt.random()
                },
                onClearContentClick = { promptValue = "" }
            )

            Spacer(Modifier.height(10.dp))
            StepSectionHeader("Choose Style", 2)
            Spacer(Modifier.height(10.dp))
            StyleList(
                modifier = Modifier.fillMaxWidth(),
                styleList = StylePreset.entries.toList(),
                selectedStyleId = selectedStyleId,
                onStyleSelected = { styleId -> selectedStyleId = styleId }
            )
            StepSectionHeader("Choose Canvas", 3)
            CanvasList(
                modifier = Modifier.fillMaxWidth(),
                canvasList = CanvasPreset.entries.toList(),
                selectedCanvasId = selectedCanvasId,
                onCanvasSelected = { canvasId -> selectedCanvasId = canvasId }
            )
            Spacer(Modifier.height(80.dp))
        }

        DrawingButton(
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(start = 30.dp, end = 30.dp, bottom = 30.dp),
            enabled = promptValue.isNotEmpty() && selectedStyleId.isNotEmpty(),
            onClick = {
                onTextToImageDrawClicked(
                    promptValue,
                    selectedStyleId,
                    selectedCanvasId
                )
            }
        )
    }
}

@Composable
private fun EnterPromptCard(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onRandomContentClick: () -> Unit,
    onClearContentClick: () -> Unit
) {
    CustomTextField(
        onValueChange = onValueChange,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 10.sp),
        value = value,
        hint = "Enter Prompt (You can try to use descriptive statements)",
        leadingIcon = {
            Icon(
                modifier = Modifier.clickable { onRandomContentClick() },
                tint = MaterialTheme.colorScheme.onSecondary,
                painter = painterResource(SharedRes.images.ic_dice),
                contentDescription = null,
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onClearContentClick() },
                    tint = MaterialTheme.colorScheme.onSecondary,
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
                text = "Draw",
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
            )
        }
    )
}