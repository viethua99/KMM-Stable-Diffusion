plugins {
    id("texttoimage.android.library")
    id("texttoimage.android.hilt")
    id("texttoimage.android.room")
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.vproject.texttoimage.core.testing.TextToImageTestRunner"
    }
    namespace = "com.vproject.texttoimage.core.database"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)

    androidTestImplementation(project(":core:testing"))
}
