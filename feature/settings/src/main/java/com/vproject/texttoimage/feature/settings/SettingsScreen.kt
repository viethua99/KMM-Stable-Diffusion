package com.vproject.texttoimage.feature.settings

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vproject.texttoimage.core.designsystem.component.TextToImageSlider
import com.vproject.texttoimage.core.designsystem.component.TextToImageSwitch
import com.vproject.texttoimage.core.designsystem.component.TextToImageTopAppBar
import com.vproject.texttoimage.core.designsystem.icon.TextToImageIcons
import com.vproject.texttoimage.core.model.data.DarkThemeConfig
import com.vproject.texttoimage.core.model.data.UserEditableSettings

@Composable
internal fun SettingsRoute(
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsScreen(
        settingsUiState = settingsUiState,
        onBackClick = onBackClick,
        onPromptCfgScaleValueChange = viewModel::updatePromptCfgScaleValue,
        onPromptStepValueChange = viewModel::updatePromptStepValue,
        onChangeDarkThemeConfig = viewModel::updateDarkThemeConfig,
    )
}

@Composable
internal fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsUiState: SettingsUiState,
    onBackClick: () -> Unit,
    onPromptCfgScaleValueChange: (Float) -> Unit,
    onPromptStepValueChange: (Float) -> Unit,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit
) {
    Column(
        modifier = modifier.testTag(SettingsTestTags.SettingContent),
    ) {
        SettingTopAppBar(onBackClick = onBackClick)
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp)
        ) {
            if (settingsUiState is SettingsUiState.Success) {
                SettingSectionTitle(text = stringResource(R.string.advanced_prompt_option))
                AdvancedPromptOptionSectionCard(
                    settings = settingsUiState.settings,
                    onPromptCfgScaleValueChange = onPromptCfgScaleValueChange,
                    onPromptStepValueChange = onPromptStepValueChange
                )
                SettingSectionTitle(text = stringResource(R.string.general))
                GeneralSectionCard(
                    settings = settingsUiState.settings,
                    onChangeDarkThemeConfig = onChangeDarkThemeConfig,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingTopAppBar(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    TextToImageTopAppBar(
        modifier = modifier.testTag(SettingsTestTags.SettingTopAppBar),
        titleRes = R.string.settings,
        navigationIcon = TextToImageIcons.RoundedArrowBack,
        navigationIconContentDescription = "Navigation icon",
        onNavigationClick = onBackClick
    )
}

@Composable
private fun SettingSectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
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
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                Column {
                    AdvancedPromptOptionItem(
                        titleText = stringResource(id = R.string.cfg_scale),
                        advancedExplanation = stringResource(id = R.string.cfg_scale_explanation),
                        optionValue = settings.promptCfgScaleValue,
                        optionValueRange = 1f..20f,
                        onOptionValueChange = onPromptCfgScaleValueChange
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AdvancedPromptOptionItem(
                        titleText = stringResource(id = R.string.steps),
                        advancedExplanation = stringResource(id = R.string.steps_explanation),
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
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
    )
    TextToImageSlider(
        valueRange = optionValueRange,
        value = optionValue,
        onValueChange = onOptionValueChange
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        advancedExplanation,
        style = MaterialTheme.typography.bodyMedium,
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
                            text = stringResource(settingItem.titleTextId),
                            trailingContent = {
                                when (settingItem) {
                                    GeneralSettingType.DISPLAY_LANGUAGE -> {
                                        Text(
                                            text = "English",
                                            style = MaterialTheme.typography.bodyLarge,
                                            textAlign = TextAlign.End,
                                        )
                                    }

                                    GeneralSettingType.DARK_MODE -> {
                                        TextToImageSwitch(
                                            modifier = Modifier.testTag(SettingsTestTags.DarkModeSwitch),
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
            imageVector = leadingIcon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        trailingContent()
        Spacer(modifier = Modifier.width(8.dp))
    }
}

internal object SettingsTestTags {
    const val SettingContent = "SettingContent"
    const val SettingTopAppBar = "SettingTopAppBar"
    const val DarkModeSwitch = "DarkModeSwitch"
}


@Preview
@Composable
private fun GeneralSettingRowPreview() {
    GeneralSettingRow(
        leadingIcon = TextToImageIcons.OutlinedDarkMode,
        text = stringResource(id = R.string.dark_mode),
        trailingContent = {}
    )
}
