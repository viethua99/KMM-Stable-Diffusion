package com.vproject.brushai.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vproject.brushai.core.designsystem.icon.BrushAiIcons

/**
 * Brush AI filled button with generic content slot. Wraps Material 3 [Button].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param contentPadding The spacing values to apply internally between the container and the
 * content.
 * @param content The button content.
 */
@Composable
fun BrushAiFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        content = content,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

/**
 * Brush AI filled button with text and icon content slots.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 */
@Composable
fun BrushAiFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    val contentPadding =
        if (leadingIcon != null) ButtonDefaults.ButtonWithIconContentPadding else ButtonDefaults.ContentPadding
    BrushAiFilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding
    ) {
        BrushAiButtonContent(text = text, leadingIcon = leadingIcon)
    }
}

/**
 * Brush AI outlined button with generic content slot. Wraps Material 3 [OutlinedButton].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param contentPadding The spacing values to apply internally between the container and the
 * content.
 * @param content The button content.
 */
@Composable
fun BrushAiOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    val borderColor = if (enabled) {
        MaterialTheme.colorScheme.outline
    } else {
        MaterialTheme.colorScheme.onSurface.copy(
            alpha = BrushAiButtonDefaults.DisabledOutlinedButtonBorderAlpha,
        )
    }
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        content = content,
        border = BorderStroke(
            width = BrushAiButtonDefaults.OutlinedButtonBorderWidth,
            color = borderColor,
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
    )
}

/**
 * Brush AI outlined button with text and icon content slots.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 */
@Composable
fun BrushAiOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    val contentPadding = if (leadingIcon != null) {
        ButtonDefaults.ButtonWithIconContentPadding
    } else {
        ButtonDefaults.ContentPadding
    }
    BrushAiOutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
    ) {
        BrushAiButtonContent(
            text = text,
            leadingIcon = leadingIcon,
        )
    }
}

/**
 * Brush AI text button with generic content slot. Wraps Material 3 [TextButton].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param content The button content.
 */
@Composable
fun BrushAiTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        content = content,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
    )
}

/**
 * Brush AI text button with text and icon content slots.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 */
@Composable
fun BrushAiTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    BrushAiTextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        BrushAiButtonContent(
            text = text,
            leadingIcon = leadingIcon,
        )
    }
}

/**
 * Brush AI toggle button with icon and checked icon content slots. Wraps Material 3
 * [IconButton].
 *
 * @param checked Whether the toggle button is currently checked.
 * @param onCheckedChange Called when the user clicks the toggle button and toggles checked.
 * @param modifier Modifier to be applied to the toggle button.
 * @param enabled Controls the enabled state of the toggle button. When `false`, this toggle button
 * will not be clickable and will appear disabled to accessibility services.
 * @param icon The icon content to show when unchecked.
 * @param checkedIcon The icon content to show when checked.
 */
@Composable
fun BrushAiIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    checkedIcon: @Composable () -> Unit = icon,
) {
    FilledIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = IconButtonDefaults.iconToggleButtonColors(
            checkedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            checkedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = if (checked) {
                MaterialTheme.colorScheme.onBackground.copy(
                    alpha = BrushAiButtonDefaults.DisabledIconButtonContainerAlpha,
                )
            } else {
                Color.Transparent
            },
        ),
    ) {
        if (checked) checkedIcon() else icon()
    }
}

/**
 * Internal Brush AI button content layout for arranging the text label and leading icon.
 *
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Default is `null` for no leading icon.
 */
@Composable
private fun BrushAiButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    leadingIcon?.let { nonNullLeadingIcon ->
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            nonNullLeadingIcon()
        }
    }

    val leadingIconStartPadding = if (leadingIcon != null) ButtonDefaults.IconSpacing else 0.dp
    Box(Modifier.padding(start = leadingIconStartPadding)) {
        text()
    }
}

/**
 * Brush AI button default values.
 */
object BrushAiButtonDefaults {
    const val DisabledOutlinedButtonBorderAlpha = 0.12f
    const val DisabledIconButtonContainerAlpha = 0.12f
    val OutlinedButtonBorderWidth = 1.dp
}

@Preview
@Composable
fun BrushAiFilledButtonPreview() {
    BrushAiFilledButton(onClick = {}) {}
}

@Preview
@Composable
fun BrushAiFilledWithIconAndContentButtonPreview() {
    BrushAiFilledButton(
        onClick = {},
        text = { Text(text = "Filled Button") },
        leadingIcon = {
            Icon(imageVector = BrushAiIcons.RoundedAutoFixNormal, contentDescription = null)
        },
    )
}

@Preview
@Composable
fun BrushAiFilledWithContentButtonPreview() {
    BrushAiFilledButton(
        onClick = {},
        text = { Text(text = "Filled Button") }
    )
}

@Preview
@Composable
fun BrushAiOutlinedButtonPreview() {
    BrushAiOutlinedButton(onClick = {}) {}
}

@Preview
@Composable
fun BrushAiOutlinedWithIconAndContentButtonPreview() {
    BrushAiOutlinedButton(
        onClick = {},
        text = { Text(text = "Outlined Button") },
        leadingIcon = {
            Icon(imageVector = BrushAiIcons.RoundedAutoFixNormal, contentDescription = null)
        },
    )
}

@Preview
@Composable
fun BrushAiOutlinedWithContentButtonPreview() {
    BrushAiOutlinedButton(
        onClick = {},
        text = { Text(text = "Outlined Button") }
    )
}

@Preview
@Composable
fun BrushAiTextButtonPreview() {
    BrushAiTextButton(onClick = {}) {}
}

@Preview
@Composable
fun BrushAiTextWithIconAndContentButtonPreview() {
    BrushAiTextButton(
        onClick = {},
        text = { Text(text = "Text Button") },
        leadingIcon = {
            Icon(imageVector = BrushAiIcons.RoundedAutoFixNormal, contentDescription = null)
        },
    )
}

@Preview
@Composable
fun BrushAiTextWithContentButtonPreview() {
    BrushAiTextButton(
        onClick = {},
        text = { Text(text = "Text Button") }
    )
}

@Preview
@Composable
fun BrushAiUnCheckedIconToggleButtonPreview() {
    BrushAiIconToggleButton(
        checked = false,
        onCheckedChange = { },
        icon = {
            Icon(
                imageVector = BrushAiIcons.OutlinedLanguage,
                contentDescription = null,
            )
        },
        checkedIcon = {
            Icon(
                imageVector = BrushAiIcons.RoundedAutoFixNormal,
                contentDescription = null,
            )
        },
    )
}

@Preview
@Composable
fun BrushAiCheckedIconToggleButtonPreview() {
    BrushAiIconToggleButton(
        checked = true,
        onCheckedChange = { },
        icon = {
            Icon(
                imageVector = BrushAiIcons.OutlinedLanguage,
                contentDescription = null,
            )
        },
        checkedIcon = {
            Icon(
                imageVector = BrushAiIcons.RoundedAutoFixNormal,
                contentDescription = null,
            )
        },
    )
}