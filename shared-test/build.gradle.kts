plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
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
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.testing)
    kapt(libs.hilt.android.compiler)
}