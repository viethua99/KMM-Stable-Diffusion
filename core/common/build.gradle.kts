plugins {
    id("brushai.android.library")
    id("brushai.android.hilt")
}

android {
    namespace = "com.vproject.brushai.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(project(":core:testing"))
}