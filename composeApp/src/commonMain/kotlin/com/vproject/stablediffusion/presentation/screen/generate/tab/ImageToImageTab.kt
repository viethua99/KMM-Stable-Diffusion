package com.vproject.stablediffusion.presentation.screen.generate.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vproject.stablediffusion.model.CanvasPreset
import com.vproject.stablediffusion.model.StylePreset
import com.vproject.stablediffusion.presentation.component.CanvasList
import com.vproject.stablediffusion.presentation.component.CustomFilledButton
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.StepSectionHeader
import com.vproject.stablediffusion.presentation.component.StyleList

@Composable
fun ImageToImageTab() {
    var selectedStyleId by remember { mutableStateOf(StylePreset.entries[0].id) }
    var selectedCanvasId by remember { mutableStateOf(CanvasPreset.entries[0].id) }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            Modifier.padding(horizontal = 10.dp, vertical = 10.dp).verticalScroll(
                rememberScrollState()
            )
        ) {
            StepSectionHeader("Reference Image", 1)
            Spacer(Modifier.height(10.dp))
            EnterPromptCard(modifier = Modifier.fillMaxWidth().height(100.dp))
            Spacer(Modifier.height(10.dp))
            StepSectionHeader("Choose Style", 2)
            Spacer(Modifier.height(10.dp))
            StyleList(
                modifier = Modifier.fillMaxWidth(),
                styleList = StylePreset.entries.toList(),
                selectedStyleId = selectedStyleId,
                onStyleSelected = { styleId -> selectedStyleId = styleId }
            )
            Spacer(Modifier.height(5.dp))
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
            enabled = false,
            onClick = { }
        )
    }
}

@Composable
private fun EnterPromptCard(
    modifier: Modifier = Modifier,
    onUploadImageClicked: () -> Unit = {}
) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.DarkGray)
            .clickable { onUploadImageClicked() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSurface,
                imageVector = CustomIcons.Home,
                contentDescription = null
            )
            Spacer(Modifier.width(5.dp).fillMaxHeight())
            Text(
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                text = "UPLOAD IMAGE",
                maxLines = 1
            )
        }
    }
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
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
            )
        }
    )
}