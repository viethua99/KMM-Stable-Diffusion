plugins {
    id("texttoimage.android.feature")
    id("texttoimage.android.library.compose")
}

android {
    namespace = "com.vproject.texttoimage.feature.generate"
}

dependencies {
    implementation(libs.androidx.compose.material.windowSizeClass)
}