package com.vproject.stablediffusion.util

import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

/**
 * Platform's context.
 */
actual typealias PlatformContext = android.app.Application

actual val platformContext: PlatformContext
    @Composable
    @ReadOnlyComposable
    get() = LocalContext.current.applicationContext as PlatformContext

actual suspend fun PlatformContext.getText(): String? = runCatching {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = clipboard.primaryClip
    if (clip != null && clip.itemCount > 0) {
        val item = clip.getItemAt(0)
        return item.text.toString()
    }
    return null
}.getOrNull()

actual suspend fun PlatformContext.setText(text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("text", text))
}

actual suspend fun PlatformContext.share() {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT,"this is my text to send")
        type = "text/plain"
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    val shareIntent = Intent.createChooser(intent, null)
    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    this.startActivity(shareIntent)
}