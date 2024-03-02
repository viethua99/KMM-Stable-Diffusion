package com.vproject.stablediffusion.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vproject.stablediffusion.SharedRes
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun StepSectionHeader(
    title: String,
    stepNumber: Int
) {
    val stepIcon = when (stepNumber) {
        1 -> SharedRes.images.ic_step1
        2 -> SharedRes.images.ic_step2
        3 -> SharedRes.images.ic_step3
        4 -> SharedRes.images.ic_step4
        else -> null
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        stepIcon?.let { nonNullStepIcon ->
            Icon(
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.onSurface,
                painter = painterResource(nonNullStepIcon),
                contentDescription = null
            )
        }

        val leadingIconStartPadding = if (stepIcon != null) 4.dp else 0.dp
        Box(Modifier.padding(start = leadingIconStartPadding)) {
            Text(
                title,
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                ),
            )
        }
    }
}