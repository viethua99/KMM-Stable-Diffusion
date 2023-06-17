plugins {
    id("brushai.android.feature")
    id("brushai.android.library.compose")
}

android {
    namespace = "com.vproject.brushai.feature.home"
}

dependencies {
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.windowSizeClass)
    implementation(libs.androidx.compose.ui.tooling.preview)
}