plugins {
    id("texttoimage.android.library")
    id("texttoimage.android.library.compose")
    id("texttoimage.android.hilt")
}

android {
    namespace = "com.vproject.texttoimage.core.testing"
}

dependencies {
    api(libs.androidx.compose.ui.test.junit)
    api(libs.androidx.test.core.ktx)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.junit4)
    api(libs.kotlinx.coroutines.test)

    debugApi(libs.androidx.compose.ui.test.manifest)
    debugApi(libs.androidx.test.monitor)

    implementation(project(":core:data"))
    implementation(project(":core:model"))
}