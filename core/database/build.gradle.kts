plugins {
    id("brushai.android.library")
    id("brushai.android.hilt")
    id("brushai.android.room")
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.vproject.brushai.core.testing.BrushAiTestRunner"
    }
    namespace = "com.vproject.brushai.core.database"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)

    androidTestImplementation(project(":core:testing"))
}
