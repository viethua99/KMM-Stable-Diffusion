package com.vproject.stablediffusion.presentation.screen.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.vproject.stablediffusion.model.DarkThemeConfig
import com.vproject.stablediffusion.model.GeneralSettingType
import com.vproject.stablediffusion.model.UserEditableSettings
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.CustomSlider
import com.vproject.stablediffusion.presentation.component.CustomSwitch
import com.vproject.stablediffusion.presentation.component.StableDiffusionTopBar

object SettingScreen: Screen {
    @Composable
    override fun Content() {
        val test = UserEditableSettings(
            10f, 25f, DarkThemeConfig.DARK
        )
        Column(
            modifier = Modifier,
        ) {
            SettingTopAppBar(onBackClick = {})
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                SettingSectionTitle(text = "Advanced prompt options")
                AdvancedPromptOptionSectionCard(
                    settings = test,
                    onPromptCfgScaleValueChange = {},
                    onPromptStepValueChange = {}
                )
                SettingSectionTitle(text = "General")
                GeneralSectionCard(
                    settings = test,
                    onChangeDarkThemeConfig = {},
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingTopAppBar(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    StableDiffusionTopBar(
        modifier = modifier,
        title = "Settings",
        navigationIcon = CustomIcons.Home,
        navigationIconContentDescription = "Navigation icon",
        onNavigationClick = onBackClick
    )
}

@Composable
private fun SettingSectionTitle(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        ),
        modifier = Modifier.padding(top = 16.dp, bottom = 10.dp),
    )
}

@Composable
private fun AdvancedPromptOptionSectionCard(
    modifier: Modifier = Modifier,
    settings: UserEditableSettings,
    onPromptCfgScaleValueChange: (Float) -> Unit,
    onPromptStepValueChange: (Float) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                Column {
                    AdvancedPromptOptionItem(
                        titleText = "CFG scale",
                        advancedExplanation = "Higher values will keep your artwork more in line with your prompt",
                        optionValue = settings.promptCfgScaleValue,
                        optionValueRange = 1f..20f,
                        onOptionValueChange = onPromptCfgScaleValueChange
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AdvancedPromptOptionItem(
                        titleText = "Steps",
                        advancedExplanation = "Running more steps means better image quality but generating may take more time",
                        optionValue = settings.promptStepValue,
                        optionValueRange = 10f..50f,
                        onOptionValueChange = onPromptStepValueChange
                    )
                }
            }
        }
    }
}

@Composable
private fun AdvancedPromptOptionItem(
    titleText: String,
    advancedExplanation: String,
    optionValue: Float,
    optionValueRange: ClosedFloatingPointRange<Float>,
    onOptionValueChange: (Float) -> Unit
) {
    Text(
        titleText,
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        modifier = Modifier
    )
    CustomSlider(
        valueRange = optionValueRange,
        value = optionValue,
        onValueChange = onOptionValueChange
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        advancedExplanation,
        style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        modifier = Modifier
    )
}

@Composable
private fun GeneralSectionCard(
    modifier: Modifier = Modifier,
    settings: UserEditableSettings,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                Column {
                    for (settingItem in GeneralSettingType.values().asList()) {
                        GeneralSettingRow(
                            leadingIcon = settingItem.leadingIcon,
                            text = settingItem.title,
                            trailingContent = {
                                when (settingItem) {
                                    GeneralSettingType.DISPLAY_LANGUAGE -> {
                                        Text(
                                            text = "English",
                                            style = TextStyle(
                                                color = MaterialTheme.colorScheme.onSurface,
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 14.sp
                                            ),
                                            textAlign = TextAlign.End,
                                        )
                                    }

                                    GeneralSettingType.DARK_MODE -> {
                                        CustomSwitch(
                                            isChecked = settings.darkThemeConfig == DarkThemeConfig.DARK,
                                            onCheckedChange = { isChecked ->
                                                onChangeDarkThemeConfig(if (isChecked) DarkThemeConfig.DARK else DarkThemeConfig.LIGHT)
                                            }
                                        )
                                    }

                                    else -> {
                                        Box(modifier = Modifier.height(35.dp))
                                    }
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GeneralSettingRow(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector,
    text: String,
    trailingContent: @Composable (() -> Unit)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = MaterialTheme.colorScheme.onSurface,
            imageVector = leadingIcon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            modifier = Modifier.weight(1f)
        )

        trailingContent()
        Spacer(modifier = Modifier.width(8.dp))
    }
}