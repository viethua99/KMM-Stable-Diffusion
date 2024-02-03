package com.vproject.stablediffusion.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepSectionHeader(
    title: String,
    stepNumber: Int
) {
//    val stepIcon = when (stepNumber) {
//        1 -> CustomIcons.Step1
//        2 -> CustomIcons.Step2
//        3 -> CustomIcons.Step3
//        else -> null
//    }
    val stepIcon = null

    Row(verticalAlignment = Alignment.CenterVertically) {
//        stepIcon?.let { nonNullStepIcon ->
//            Icon(
//                tint = MaterialTheme.colorScheme.onSurface,
//                imageVector = nonNullStepIcon,
//                contentDescription = null
//            )
//        }

        val leadingIconStartPadding = if (stepIcon != null) ButtonDefaults.IconSpacing else 0.dp
        Box(Modifier.padding(start = leadingIconStartPadding)) {
            Text(
                "${stepNumber}. $title",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
            )
        }
    }
}