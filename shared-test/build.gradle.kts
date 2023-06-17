plugins {
    id("brushai.android.library")
    id("brushai.android.hilt")
}

android {
    namespace = "com.vproject.shared_test"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }
}

dependencies {
    implementation(project(":app"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit4)
    implementation(libs.androidx.test.core.ktx)
    implementation(libs.androidx.test.ext.ktx)
    implementation(libs.androidx.test.rules)
    implementation(libs.androidx.test.runner)
}