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
import com.vproject.stablediffusion.model.CanvasPreset
import com.vproject.stablediffusion.model.StylePreset
import com.vproject.stablediffusion.presentation.component.CanvasList
import com.vproject.stablediffusion.presentation.component.CustomFilledButton
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.CustomTextField
import com.vproject.stablediffusion.presentation.component.StepSectionHeader
import com.vproject.stablediffusion.presentation.component.StyleList

@Composable
fun TextToImageTab(
    onDrawClicked: (prompt: String, styleId: String, canvasId: String) -> Unit = {_ , _, _ -> }
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
            onClick = { onDrawClicked(promptValue, selectedStyleId, selectedCanvasId ) }
        )
    }
}

@Composable
private fun EnterPromptCard(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearContentClick: () -> Unit

) {
    CustomTextField(
        onValueChange = onValueChange,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 10.sp),
        value = value,
        hint = "Enter Prompt (You can try to use descriptive statements)",
        leadingIcon = {
            Icon(
                tint = MaterialTheme.colorScheme.onSecondary,
                imageVector = CustomIcons.DefaultHistory,
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