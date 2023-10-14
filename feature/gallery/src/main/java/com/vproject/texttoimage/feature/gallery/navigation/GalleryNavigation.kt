package com.vproject.texttoimage.feature.gallery.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vproject.texttoimage.feature.gallery.GalleryRoute

const val galleryRoute = "gallery_route"

fun NavController.navigateToGallery(navOptions: NavOptions? = null) {
    this.navigate(galleryRoute, navOptions)
}

fun NavGraphBuilder.galleryScreen(
    onPromptItemClick: (promptContent: String, imageUrl: String) -> Unit
) {
    composable(route = galleryRoute) {
        GalleryRoute(onPromptItemClick = onPromptItemClick)
    }
}