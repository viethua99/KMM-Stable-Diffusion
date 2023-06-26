plugins {
    id("brushai.android.library")
    id("brushai.android.hilt")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.vproject.brushai.core.network"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}


dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    testImplementation(project(":core:testing"))
}
