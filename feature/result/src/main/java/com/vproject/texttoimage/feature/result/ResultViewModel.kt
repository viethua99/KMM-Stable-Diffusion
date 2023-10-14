package com.vproject.texttoimage.feature.result

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.MediaScannerConnection
import android.os.Environment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.vproject.texttoimage.core.domain.GetFavorableStyleUseCase
import com.vproject.texttoimage.feature.result.navigation.ResultArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
internal class ResultViewModel @Inject constructor(
    @ApplicationContext  val appContext: Context,
    savedStateHandle: SavedStateHandle,
    getFavorableStyleUseCase: GetFavorableStyleUseCase
) : ViewModel() {
    private val resultArgs = ResultArgs(savedStateHandle)

    val resultUiState: StateFlow<ResultUiState> =
        getFavorableStyleUseCase(resultArgs.styleId).map { favorableStyle ->
            ResultUiState.ShowResult(
                resultArgs.imageUrl,
                resultArgs.prompt,
                favorableStyle.style.name
            )
        }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ResultUiState.Empty
            )



    private fun persistImage(file: File, bitmap: Bitmap) {
        val os: OutputStream
        try {
            os = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.flush()
            os.close()

            MediaScannerConnection.scanFile(
                appContext, arrayOf(file.toString()),
                null, null
            )


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


     fun downloadImageFromUrl(url: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val loader = ImageLoader(appContext)
            val request = ImageRequest.Builder(appContext)
                .data(url)
                .allowHardware(false) // Disable hardware bitmaps.
                .build()

            val result = (loader.execute(request) as SuccessResult).drawable

            val bitmap = (result as BitmapDrawable).bitmap

            val path =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/TextToImage") //Creates app specific folder

            if (!path.exists())
                path.mkdirs()

            val imageFile = File(path, "texttoimage-${(0..1000).random()}.jpg")
            if (imageFile.exists()) {
                //File Name Already Exist Do Whatever
            } else {
                persistImage(imageFile, bitmap)
            }
        }
    }
}