package com.vproject.texttoimage.feature.result.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vproject.texttoimage.feature.result.ResultRoute
import java.net.URLDecoder
import java.net.URLEncoder

private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

@VisibleForTesting
internal const val imageUrlArg = "imageUrlArg"

@VisibleForTesting
internal const val promptArg = "promptArg"

@VisibleForTesting
internal const val styleIdArg = "styleIdArg"

const val resultRoute = "result_route"

internal class ResultArgs(val imageUrl: String, val prompt: String, val styleId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(URLDecoder.decode(checkNotNull(savedStateHandle[imageUrlArg]), URL_CHARACTER_ENCODING),
                URLDecoder.decode(checkNotNull(savedStateHandle[promptArg]), URL_CHARACTER_ENCODING),
                URLDecoder.decode(checkNotNull(savedStateHandle[styleIdArg]), URL_CHARACTER_ENCODING))
}

fun NavController.navigateToResult(imageUrl: String, prompt: String, styleId: String) {
    val encodedUrl = URLEncoder.encode(imageUrl, URL_CHARACTER_ENCODING)
    this.navigate("$resultRoute/$encodedUrl/$prompt/$styleId") {
        popUpTo(graph.findStartDestination().id)
        launchSingleTop = true
    }
}

fun NavGraphBuilder.resultScreen() {
    composable(
        route = "$resultRoute/{$imageUrlArg}/{$promptArg}/{$styleIdArg}",
        arguments = listOf(
            navArgument(imageUrlArg) { type = NavType.StringType },
            navArgument(promptArg) { type = NavType.StringType },
            navArgument(styleIdArg) { type = NavType.StringType },
            )
    ) {
        ResultRoute()
    }
}