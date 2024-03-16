package com.vproject.stablediffusion.util

import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

/**
 * Platform's context.
 */
actual object PlatformContext

actual val platformContext: PlatformContext = PlatformContext

actual suspend fun PlatformContext.getText(): String? {
    return runCatching {
        Toolkit.getDefaultToolkit().systemClipboard.getData(DataFlavor.stringFlavor) as? String
    }.getOrNull()
}

actual suspend fun PlatformContext.setText(text: String) {
    Toolkit.getDefaultToolkit().systemClipboard.setContents(
        StringSelection(text),
        StringSelection(text)
    )
}

actual suspend fun PlatformContext.share() {

}