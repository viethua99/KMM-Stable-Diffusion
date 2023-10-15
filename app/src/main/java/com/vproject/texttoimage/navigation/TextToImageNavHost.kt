package com.vproject.texttoimage.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.vproject.texttoimage.feature.gallery.navigation.galleryScreen
import com.vproject.texttoimage.feature.generate.navigation.generateRoute
import com.vproject.texttoimage.feature.generate.navigation.generateScreen
import com.vproject.texttoimage.feature.generate.navigation.navigateToGenerate
import com.vproject.texttoimage.feature.loading.navigation.loadingScreen
import com.vproject.texttoimage.feature.loading.navigation.navigateToLoading
import com.vproject.texttoimage.feature.result.navigation.navigateToResult
import com.vproject.texttoimage.feature.result.navigation.resultScreen
import com.vproject.texttoimage.feature.settings.navigation.settingsScreen
import com.vproject.texttoimage.ui.TextToImageAppState

@Composable
fun TextToImageNavHost(
    appState: TextToImageAppState,
    modifier: Modifier = Modifier,
    startDestination: String = generateRoute,
) {
    val navController = appState.navController
    val context  = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        generateScreen(
            onGenerateButtonClicked = { prompt, styleId ->
                navController.navigateToLoading(prompt, styleId)
            }
        )
        galleryScreen(onPromptItemClick = { promptContent, imageUrl ->
            navController.navigateToResult(imageUrl, promptContent,"1")
        })
        settingsScreen(
            onBackClick = navController::popBackStack,
        )
        loadingScreen(
            onImageGenerated = navController::navigateToResult,
            onError = {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                navController.navigateToGenerate()
            }
        )
        resultScreen(
            onBackClick = navController::popBackStack
        )
    }
}