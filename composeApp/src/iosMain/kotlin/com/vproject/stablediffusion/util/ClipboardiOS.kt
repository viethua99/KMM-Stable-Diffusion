package com.vproject.stablediffusion.util

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIPasteboard
import platform.UIKit.UIWindow
import platform.UIKit.UIApplication

/**
 * Platform's context.
 */
actual object PlatformContext

actual val platformContext: PlatformContext = PlatformContext

actual suspend fun PlatformContext.getText(): String? {
    return runCatching { UIPasteboard.generalPasteboard.string }.getOrNull()
}

actual suspend fun PlatformContext.setText(text: String) {
    UIPasteboard.generalPasteboard.string = text
}

actual suspend fun PlatformContext.share() {
    val window = UIApplication.sharedApplication.windows.last() as? UIWindow
    val currentViewController = window?.rootViewController

    val activityViewController = UIActivityViewController(listOf("This is my ext"), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}