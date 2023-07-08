plugins {
    id("brushai.android.library")
    kotlin("kapt")
}

android {
    namespace = "com.vproject.brushai.core.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)

    kapt(libs.hilt.android.compiler)

    testImplementation(project(":core:testing"))
}