plugins {
    id("texttoimage.android.library")
    id("texttoimage.android.hilt")
}

android {

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "STABLE_DIFFUSION_API_KEY", "\"${project.property("STABLE_DIFFUSION_API_KEY")}\"")
    }

    namespace = "com.vproject.texttoimage.core.network"

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
