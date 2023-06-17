plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.vproject.brushai"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.vproject.brushai"
        minSdk = 24
        targetSdk = 33
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Jetpack Compose Standard Dependencies
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
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
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation (libs.androidx.compose.ui.test.junit)
    androidTestImplementation(project(":shared-test"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit4)
    implementation(libs.androidx.test.core.ktx)
    implementation(libs.androidx.test.ext.ktx)
    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.testing)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.test.runner)

    // Jetpack Compose Debug Dependencies
    debugImplementation (libs.androidx.compose.ui.tooling)
    debugImplementation (libs.androidx.compose.ui.test.manifest)
}