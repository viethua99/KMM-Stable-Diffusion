package com.vproject.stablediffusion.presentation.screen.generate.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.vproject.stablediffusion.SharedRes
import com.vproject.stablediffusion.model.CanvasPreset
import com.vproject.stablediffusion.model.StableDiffusionMode
import com.vproject.stablediffusion.model.StylePreset
import com.vproject.stablediffusion.presentation.component.CanvasList
import com.vproject.stablediffusion.presentation.component.CustomFilledButton
import com.vproject.stablediffusion.presentation.component.CustomIcons
import com.vproject.stablediffusion.presentation.component.CustomTextField
import com.vproject.stablediffusion.presentation.component.StepSectionHeader
import com.vproject.stablediffusion.presentation.component.StyleList
import com.vproject.stablediffusion.util.PermissionCallback
import com.vproject.stablediffusion.util.PermissionStatus
import com.vproject.stablediffusion.util.PermissionType
import com.vproject.stablediffusion.util.createPermissionsManager
import com.vproject.stablediffusion.util.rememberCameraManager
import com.vproject.stablediffusion.util.rememberGalleryManager
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun ImageToImageTab(
    onImageToImageDrawClicked: (originalImage: ByteArray, prompt: String, styleId: String, canvasId: String) -> Unit = { _, _, _, _ -> }
) {
    var promptValue by remember { mutableStateOf("") }
    var selectedStyleId by remember { mutableStateOf(StylePreset.entries[0].id) }
    var selectedCanvasId by remember { mutableStateOf(CanvasPreset.entries[0].id) }
    var selectedImage by remember { mutableStateOf<ByteArray?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            StepSectionHeader("Reference Image", 1)
            Spacer(Modifier.height(10.dp))
            UploadImageCard(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                onImageSelected = { imageArray ->
                    selectedImage = imageArray
                })
            Spacer(Modifier.height(10.dp))
            StepSectionHeader("Enter Prompts", 2)
            Spacer(Modifier.height(10.dp))
            EnterPromptsCard(
                value = promptValue,
                onValueChange = { promptValue = it },
                onClearContentClick = { promptValue = "" }
            )
            Spacer(Modifier.height(10.dp))
            StepSectionHeader("Choose Style", 3)
            Spacer(Modifier.height(10.dp))
            StyleList(
                modifier = Modifier.fillMaxWidth(),
                styleList = StylePreset.entries.toList(),
                selectedStyleId = selectedStyleId,
                onStyleSelected = { styleId -> selectedStyleId = styleId }
            )
//            StepSectionHeader("Choose Canvas", 4)
//            CanvasList(
//                modifier = Modifier.fillMaxWidth(),
//                canvasList = CanvasPreset.entries.toList(),
//                selectedCanvasId = selectedCanvasId,
//                onCanvasSelected = { canvasId -> selectedCanvasId = canvasId }
//            )

            Spacer(Modifier.height(80.dp))
        }

        DrawingButton(
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(start = 30.dp, end = 30.dp, bottom = 30.dp),
            enabled = promptValue.isNotEmpty() && selectedStyleId.isNotEmpty() && selectedImage != null,
            onClick = {
                selectedImage?.let { nonNullImageArray ->
                    onImageToImageDrawClicked(
                        nonNullImageArray,
                        promptValue,
                        selectedStyleId,
                        selectedCanvasId
                    )
                }
            }
        )
    }
}

@Composable
private fun UploadImageCard(
    modifier: Modifier = Modifier,
    onImageSelected: (selectedImage: ByteArray) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var imageSourceOptionDialog by remember { mutableStateOf(value = false) }
    var launchGallery by remember { mutableStateOf(value = false) }
    var launchCamera by remember { mutableStateOf(value = false) }
    var permissionRationalDialog by remember { mutableStateOf(value = false) }
    var launchSetting by remember { mutableStateOf(value = false) }

    val permissionsManager = createPermissionsManager(object : PermissionCallback {
        override fun onPermissionStatus(
            permissionType: PermissionType,
            status: PermissionStatus
        ) {
            when (status) {
                PermissionStatus.GRANTED -> {
                    when (permissionType) {
                        PermissionType.CAMERA -> launchCamera = true
                        PermissionType.GALLERY -> launchGallery = true
                    }
                }

                else -> {
                    permissionRationalDialog = true
                }
            }
        }
    })

    val cameraManager = rememberCameraManager { sharedImage ->
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                sharedImage?.toImageBitmap()
            }
            val imageArray = withContext(Dispatchers.Default) {
                sharedImage?.toByteArray()
            }
            bitmap?.let { nonNullBitmap ->
                imageBitmap = nonNullBitmap
            }
            imageArray?.let { nonNullArray ->
                onImageSelected(nonNullArray)
            }
        }
    }

    val galleryManager = rememberGalleryManager { sharedImage ->
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                sharedImage?.toImageBitmap()
            }
            val imageArray = withContext(Dispatchers.Default) {
                sharedImage?.toByteArray()
            }
            bitmap?.let { nonNullBitmap ->
                imageBitmap = nonNullBitmap
            }
            imageArray?.let { nonNullArray ->
                onImageSelected(nonNullArray)
            }
        }
    }

    if (imageSourceOptionDialog) {
        ImageSourceOptionDialog(onDismissRequest = {
            imageSourceOptionDialog = false
        }, onGalleryRequest = {
            imageSourceOptionDialog = false
            launchGallery = true
        }, onCameraRequest = {
            imageSourceOptionDialog = false
            launchCamera = true
        })
    }
    if (launchGallery) {
        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.GALLERY)
        }
        launchGallery = false
    }
    if (launchCamera) {
        if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.CAMERA)
        }
        launchCamera = false
    }
    if (launchSetting) {
        permissionsManager.launchSettings()
        launchSetting = false
    }

    if (permissionRationalDialog) {
        AlertMessageDialog(title = "Permission Required",
            message = "To set your profile picture, please grant this permission. You can manage permissions in your device settings.",
            positiveButtonText = "Settings",
            negativeButtonText = "Cancel",
            onPositiveClick = {
                permissionRationalDialog = false
                launchSetting = true

            },
            onNegativeClick = {
                permissionRationalDialog = false
            })

    }

    Column(
        modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(Color.DarkGray)
            .clickable {
                imageSourceOptionDialog = true
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap!!,
                contentDescription = "Profile",
                modifier = Modifier.size(100.dp).clip(CircleShape).clickable {
                    imageSourceOptionDialog = true
                },
                contentScale = ContentScale.Crop
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSurface,
                painter = painterResource(SharedRes.images.ic_upload),
                contentDescription = null
            )
            Spacer(Modifier.width(5.dp).fillMaxHeight())
            Text(
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                ),
                text = "UPLOAD IMAGE",
                maxLines = 1
            )
        }
    }
}

@Composable
private fun EnterPromptsCard(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearContentClick: () -> Unit
) {
    CustomTextField(
        onValueChange = onValueChange,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 10.sp),
        value = value,
        hint = "Enter Prompt (You can try to use descriptive statements)",
        leadingIcon = {
            Icon(
                tint = MaterialTheme.colorScheme.onSecondary,
                imageVector = CustomIcons.DefaultHistory,
                contentDescription = null,
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable { onClearContentClick() },
                    tint = MaterialTheme.colorScheme.onSecondary,
                    imageVector = CustomIcons.DefaultClose,
                    contentDescription = null
                )
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
private fun DrawingButton(modifier: Modifier = Modifier, enabled: Boolean, onClick: () -> Unit) {
    CustomFilledButton(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = onClick,
        enabled = enabled,
        text = {
            Text(
                text = "Draw",
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
            )
        }
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ImageSourceOptionDialog(
    onDismissRequest: () -> Unit,
    onGalleryRequest: () -> Unit = {},
    onCameraRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface)
                .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Select an Image Source",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp).clickable {
                    onCameraRequest.invoke()
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(SharedRes.images.ic_dark_mode),
                    contentDescription = null
                )
                Text(
                    text = "Camera",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp).clickable {
                    onGalleryRequest.invoke()
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(SharedRes.images.ic_dark_mode),
                    contentDescription = null
                )
                Text(
                    text = "Gallery",
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AlertMessageDialog(
    title: String,
    message: String? = null,
    resource: String? = "ic_error_dialog.xml",
    positiveButtonText: String? = null,
    negativeButtonText: String? = null,
    onPositiveClick: () -> Unit = {},
    onNegativeClick: () -> Unit = {},
) {

    Dialog(
        onDismissRequest = {}, properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            shape = RoundedCornerShape(size = 12.dp)
        ) {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.onSurface)
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                resource?.let {
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(SharedRes.images.ic_dark_mode),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                message?.let {
                    Text(
                        text = it,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp, start = 16.dp)
                ) {
                    negativeButtonText?.let {
                        Button(
                            modifier = Modifier.weight(1f), onClick = {
                                onNegativeClick()
                            }, colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(text = it, textAlign = TextAlign.Center, maxLines = 1)
                        }

                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    positiveButtonText?.let {
                        Button(
                            modifier = Modifier.weight(1f), onClick = {
                                onPositiveClick()
                            }, colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(text = it, textAlign = TextAlign.Center, maxLines = 1)
                        }
                    }
                }
            }

        }

    }
}