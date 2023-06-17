plugins {
    id("brushai.android.application")
    id("brushai.android.application.compose")
    id("brushai.android.hilt")
}

android {
    namespace = "com.vproject.brushai"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.vproject.brushai"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.vproject.brushai.runner.CustomTestRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":feature:home"))

    // Jetpack Compose Standard Dependencies
    implementation(libs.androidx.activity.compose)

    // Android Kotlin Extension Dependencies
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    // Unit Testing Dependencies
    testImplementation(project(":shared-test"))

    // UI Testing Dependencies
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)
    androidTestImplementation (libs.androidx.compose.ui.test.junit)
    androidTestImplementation(project(":shared-test"))

    implementation(libs.junit4)
}