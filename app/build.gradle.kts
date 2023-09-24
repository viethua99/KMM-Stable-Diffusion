plugins {
    id("texttoimage.android.application")
    id("texttoimage.android.application.compose")
    id("texttoimage.android.hilt")
}

android {
    namespace = "com.vproject.texttoimage"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.vproject.texttotimage"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.vproject.texttoimage.core.testing.TextToImageTestRunner"

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
    implementation(project(":feature:loading"))
    implementation(project(":feature:result"))

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