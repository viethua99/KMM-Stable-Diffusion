package com.vproject.stablediffusion.presentation.screen.generate

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.vproject.stablediffusion.repository.image.ImageRepository
import com.vproject.stablediffusion.util.TestUtil
import com.vproject.stablediffusion.util.imageBitmapFromBytes
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class GenerateModel(private val imageRepository: ImageRepository) : StateScreenModel<GenerateUiState>(GenerateUiState.Initial) {

}