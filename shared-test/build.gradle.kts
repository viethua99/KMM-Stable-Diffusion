plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    implementation("junit:junit:4.13.2")
    implementation("androidx.test:core-ktx:1.5.0")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.test:rules:1.5.0")
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("com.google.dagger:hilt-android-testing:2.44")
    implementation("androidx.test:runner:1.5.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
}