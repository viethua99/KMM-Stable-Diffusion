import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vproject.stablediffusion.di.initKoin

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        initKoin ()
        App()
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App()
}