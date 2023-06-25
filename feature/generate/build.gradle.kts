plugins {
    id("brushai.android.feature")
    id("brushai.android.library.compose")
}

android {
    namespace = "com.vproject.brushai.feature.generate"
}

dependencies {
    implementation(libs.androidx.compose.material.windowSizeClass)
}