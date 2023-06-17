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
    // Jetpack Compose Standard Dependencies
    implementation(libs.androidx.activity.compose)
    implementation (libs.androidx.compose.ui)
    implementation (libs.androidx.compose.ui.graphics)
    implementation (libs.androidx.compose.ui.tooling.preview)
    implementation (libs.androidx.compose.material)

    // Android Kotlin Extension Dependencies
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Unit Testing Dependencies
    testImplementation (libs.junit4)
    testImplementation(project(":shared-test"))

    // UI Testing Dependencies
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)
    androidTestImplementation (libs.androidx.compose.ui.test.junit)
    androidTestImplementation(project(":shared-test"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit4)
    implementation(libs.androidx.test.core.ktx)
    implementation(libs.androidx.test.ext.ktx)
    implementation(libs.androidx.test.rules)
    implementation(libs.androidx.test.runner)

    // Jetpack Compose Debug Dependencies
    debugImplementation (libs.androidx.compose.ui.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)
}