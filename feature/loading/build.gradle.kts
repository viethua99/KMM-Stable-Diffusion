plugins {
    id("texttoimage.android.feature")
    id("texttoimage.android.library.compose")
}

android {
    namespace = "com.vproject.texttoimage.feature.loading"
}

dependencies {
    implementation(libs.lottie.compose)
}