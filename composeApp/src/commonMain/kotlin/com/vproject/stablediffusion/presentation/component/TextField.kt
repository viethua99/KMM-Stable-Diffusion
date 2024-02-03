package com.vproject.stablediffusion.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Custom text field.
 *
 * @param onValueChange Will be called when the user type their input.
 * @param value The text field content.
 * @param modifier Modifier to be applied to the text field.
 * @param hint The text hint when there is no content in text field.
 * @param subHint The small text hint below the main hint when there is no content in text field.
 * @param leadingIcon The button leading icon content on bottom-left corner. Pass `null` here for no leading icon.
 * @param trailingIcon The button trailing icon content on bottom-right corner. Pass `null` here for no trailing icon.
 * @param textStyle The text style for the content.
 */
@Composable
fun CustomTextField(
    onValueChange: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier,
    hint: String = "",
    subHint: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    val scrollState = rememberScrollState(0)
    LaunchedEffect(scrollState.maxValue) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10))
                    .height(CustomTextFieldDefaults.Height)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(
                        top = CustomTextFieldDefaults.InnerTopPadding,
                        bottom = CustomTextFieldDefaults.InnerBottomPadding,
                        start = CustomTextFieldDefaults.InnerHorizontalPadding,
                        end = CustomTextFieldDefaults.InnerHorizontalPadding
                    )
            ) {
                if (value.isEmpty()) {
                    val hintStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = CustomTextFieldDefaults.MainHintFontSize
                    )
                    val subHintStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = CustomTextFieldDefaults.SubHintFontSize
                    )
                    CustomTextFieldHint(
                        hint = hint,
                        hintStyle = hintStyle,
                        subHint = subHint,
                        subHintStyle = subHintStyle
                    )
                }
                Column(Modifier.fillMaxSize()) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .weight(CustomTextFieldDefaults.InnerContentWeight)
                            .verticalScroll(scrollState)
                    ) {
                        innerTextField()
                    }
                    CustomTextFieldCornerIcons(
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        modifier = Modifier
                            .weight(CustomTextFieldDefaults.CornerIconsWeight)
                            .padding(top = CustomTextFieldDefaults.CornerIconsTopPadding)
                    )
                }

            }
        }
    )
}

/**
 * Internal custom text field hint and sub hint layout
 *
 * @param hint The text hint when there is no content in text field.
 * @param hintStyle The text style for the main hint.
 * @param subHint The small text hint below the main hint when there is no content in text field.
 * @param subHintStyle The text style for small hint below the main hint.
 */
@Composable
private fun CustomTextFieldHint(
    hint: String,
    hintStyle: SpanStyle = SpanStyle(),
    subHint: String = "",
    subHintStyle: SpanStyle = SpanStyle()
) {
    Text(
        buildAnnotatedString {
            withStyle(hintStyle) { append(hint) }
            if (subHint.isNotEmpty()) {
                append("\n")
                withStyle(subHintStyle) { append(subHint) }
            }
        }
    )
}

/**
 * Internal custom text field corner icons
 *
 * @param modifier Modifier to be applied to the icons layout.
 * @param leadingIcon The button leading icon content on bottom-left corner. Pass `null` here for no leading icon.
 * @param trailingIcon The button trailing icon content on bottom-right corner. Pass `null` here for no trailing icon.
 */
@Composable
private fun CustomTextFieldCornerIcons(
    modifier: Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        leadingIcon?.let { nonNullLeadingIcon ->
            nonNullLeadingIcon()
        }
        Box(modifier = Modifier.weight(2f))
        trailingIcon?.let { nonNullTrailingIcon ->
            nonNullTrailingIcon()
        }
    }
}

/**
 * Custom text field default values.
 */
private object CustomTextFieldDefaults {
    val Height = 150.dp
    val InnerTopPadding = 16.dp
    val InnerHorizontalPadding = 16.dp
    val InnerBottomPadding = 10.dp
    val CornerIconsTopPadding = 16.dp
    val MainHintFontSize = 16.sp
    val SubHintFontSize = 12.sp
    const val InnerContentWeight = 7f
    const val CornerIconsWeight = 3f
}