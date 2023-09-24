plugins {
    id("texttoimage.android.library")
    id("texttoimage.android.hilt")
}

android {
    namespace = "com.vproject.texttoimage.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(project(":core:testing"))
}