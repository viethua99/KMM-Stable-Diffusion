package com.vproject.texttoimage.feature.loading.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vproject.texttoimage.feature.loading.LoadingRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

private val URL_CHARACTER_ENCODING = UTF_8.name()

@VisibleForTesting
internal const val promptArg = "promptArg"

@VisibleForTesting
internal const val styleIdArg = "styleIdArg"

const val loadingRoute = "loading_route"

internal class LoadingArgs(val prompt: String, val styleId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[promptArg]),
                    URL_CHARACTER_ENCODING
                ),
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[styleIdArg]),
                    URL_CHARACTER_ENCODING
                )
            )
}

fun NavController.navigateToLoading(prompt: String, styleId: String) {
    val encodedPrompt = URLEncoder.encode(prompt, URL_CHARACTER_ENCODING)
    this.navigate("$loadingRoute/$encodedPrompt/$styleId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.loadingScreen(onImageGenerated: (url: String, prompt: String, styleId: String) -> Unit) {
    composable(
        route = "$loadingRoute/{$promptArg}/{$styleIdArg}",
        arguments = listOf(
            navArgument(promptArg) { type = NavType.StringType },
            navArgument(styleIdArg) { type = NavType.StringType },
        )
    ) {
        LoadingRoute(onImageGenerated = onImageGenerated)
    }
}