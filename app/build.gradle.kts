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

        testInstrumentationRunner = "com.vproject.brushai.core.testing.BrushAiTestRunner"

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

    implementation(project(":feature:generate"))
    implementation(project(":feature:explore"))
    implementation(project(":feature:settings"))

    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    androidTestImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:datastore-test"))
    androidTestImplementation(project(":core:network"))
    androidTestImplementation(kotlin("test"))

    debugImplementation(project(":ui-test-hilt-manifest"))

    // Jetpack Compose Standard Dependencies
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material.windowSizeClass)
    implementation(libs.accompanist.systemuicontroller)

    // Android Kotlin Extension Dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    // UI Testing Dependencies
    androidTestImplementation(libs.accompanist.testharness)
    androidTestImplementation(libs.androidx.navigation.testing)

    implementation(libs.junit4)
}