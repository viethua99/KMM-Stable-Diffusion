package com.vproject.texttoimage.core.designsystem.component

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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vproject.texttoimage.core.designsystem.icon.TextToImageIcons

/**
 * Text To Image text field.
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
fun TextToImageTextField(
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
                    .height(TextToImageTextFieldDefaults.Height)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(
                        top = TextToImageTextFieldDefaults.InnerTopPadding,
                        bottom = TextToImageTextFieldDefaults.InnerBottomPadding,
                        start = TextToImageTextFieldDefaults.InnerHorizontalPadding,
                        end = TextToImageTextFieldDefaults.InnerHorizontalPadding
                    )
            ) {
                if (value.isEmpty()) {
                    val hintStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = TextToImageTextFieldDefaults.MainHintFontSize
                    )
                    val subHintStyle = SpanStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = TextToImageTextFieldDefaults.SubHintFontSize
                    )
                    TextToImageTextFieldHint(
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
                            .weight(TextToImageTextFieldDefaults.InnerContentWeight)
                            .verticalScroll(scrollState)
                    ) {
                        innerTextField()
                    }
                    TextToImageTextFieldCornerIcons(
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        modifier = Modifier
                            .weight(TextToImageTextFieldDefaults.CornerIconsWeight)
                            .padding(top = TextToImageTextFieldDefaults.CornerIconsTopPadding)
                    )
                }

            }
        }
    )
}

/**
 * Internal Text To Image text field hint and sub hint layout
 *
 * @param hint The text hint when there is no content in text field.
 * @param hintStyle The text style for the main hint.
 * @param subHint The small text hint below the main hint when there is no content in text field.
 * @param subHintStyle The text style for small hint below the main hint.
 */
@Composable
private fun TextToImageTextFieldHint(
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
 * Internal Text To Image text field corner icons
 *
 * @param modifier Modifier to be applied to the icons layout.
 * @param leadingIcon The button leading icon content on bottom-left corner. Pass `null` here for no leading icon.
 * @param trailingIcon The button trailing icon content on bottom-right corner. Pass `null` here for no trailing icon.
 */
@Composable
private fun TextToImageTextFieldCornerIcons(
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
 * Text To Image text field default values.
 */
object TextToImageTextFieldDefaults {
    val Height = 200.dp
    val InnerTopPadding = 16.dp
    val InnerHorizontalPadding = 16.dp
    val InnerBottomPadding = 10.dp
    val CornerIconsTopPadding = 16.dp
    val MainHintFontSize = 18.sp
    val SubHintFontSize = 14.sp
    const val InnerContentWeight = 7f
    const val CornerIconsWeight = 3f
}

@Preview
@Composable
fun TextToImageTextFieldWithSubHintPreview() {
    TextToImageTextField(
        onValueChange = {},
        value = "",
        hint = "Enter a prompt",
        subHint = "Anything • Any detail • Add your dreams",
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15))
    )
}

@Preview
@Composable
fun TextToImageTextFieldWithoutSubHintPreview() {
    TextToImageTextField(
        onValueChange = {},
        value = "",
        hint = "Enter a prompt",
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15))

    )
}

@Preview
@Composable
fun TextToImageTextFieldWithContentPreview() {
    TextToImageTextField(
        onValueChange = {},
        value = "This is my content",
        hint = "Enter a prompt",
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15))
    )
}

@Preview
@Composable
fun TextToImageTextFieldWithLeadingIconPreview() {
    TextToImageTextField(
        onValueChange = {},
        value = "This is my content",
        hint = "Enter a prompt",
        leadingIcon = {
            Icon(
                imageVector = TextToImageIcons.OutlinedLanguage,
                contentDescription = null,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15))
    )
}

@Preview
@Composable
fun TextToImageTextFieldWithTrailingIconPreview() {
    TextToImageTextField(
        onValueChange = {},
        value = "This is my content",
        hint = "Enter a prompt",
        trailingIcon = {
            Icon(
                imageVector = TextToImageIcons.OutlinedLanguage,
                contentDescription = null,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15))
    )
}

@Preview
@Composable
fun TextToImageTextFieldWithBothIconPreview() {
    TextToImageTextField(
        onValueChange = {},
        value = "This is my content",
        hint = "Enter a prompt",
        leadingIcon = {
            Icon(
                imageVector = TextToImageIcons.OutlinedLanguage,
                contentDescription = null,
            )
        },
        trailingIcon = {
            Icon(
                imageVector = TextToImageIcons.OutlinedLanguage,
                contentDescription = null,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15))
    )
}