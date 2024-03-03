import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.ExperimentalComposeLibrary
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.pluginSerialization)
    alias(libs.plugins.buildKonfigPlugin)
    alias(libs.plugins.icerockMobilePlugin)
    alias(libs.plugins.sqlDelight)
}

version = "1.0.0"

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            // Ktor Dependencies for API network
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.okhttp)

            // Koin Dependencies for dependency injection
            implementation(libs.koin.android)
            implementation(libs.koin.workmanager)

            // SQLDelight Dependencies for local database
            implementation(libs.sqldelight.driver.android)
        }
        iosMain.dependencies {
            // Ktor Dependencies for API network
            implementation(libs.ktor.client.darwin)
            implementation(libs.moko.resources.core)

            // SQLDelight Dependencies for local database
            implementation(libs.sqldelight.driver.ios)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)

            // Ktor Dependencies for API network
            implementation(libs.ktor.client.cio)

            // SQLDelight Dependencies for local database
            implementation(libs.sqldelight.driver.jvm)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            // Ktor Dependencies for API network
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
//            implementation(libs.ktor.client.okhttp)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(libs.stately.common)

            // Koin Dependencies for dependency injection
            implementation(libs.koin.core)
            implementation(libs.koin.compose.multiplatform)

            // Voyager Dependencies for screen models
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tab.navigator)
            implementation(libs.voyager.koin)

            // Kamel Dependencies for images loading
            implementation(libs.kamel)

            // Moko Dependencies for resources
            implementation(libs.moko.resources.compose)

            // SQLDelight Dependencies for local database
            implementation(libs.bundles.sqldelight.common)

            implementation(libs.accompanist.systemuicontroller)
        }
    }

    cocoapods {
        framework {
            isStatic = false // SwiftUI preview requires dynamic framework
            linkerOpts("-lsqlite3")
//            export(libs.touchlab.kermit.simple)
            export(libs.moko.resources.core)
        }
//        podfile = project.file("../iosApp/Podfile")
    }
}

android {
    namespace = "com.vproject.stablediffusion"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    sourceSets["main"].java.srcDirs("build/generated/moko/androidMain/src")

    defaultConfig {
        applicationId = "com.vproject.stablediffusion"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.vproject.stablediffusion"
            packageVersion = "1.0.0"
        }
    }
}

buildkonfig {
    packageName = "com.vproject.stablediffusion"

    defaultConfigs {
        buildConfigField(STRING, "STABLE_DIFFUSION_API_KEY", "${project.property("STABLE_DIFFUSION_API_KEY")}")
    }
}

sqldelight {
    databases.create("StableDiffusionDatabase") {
        packageName.set("com.vproject.stablediffusion.database")
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.vproject.stablediffusion"
    multiplatformResourcesClassName = "SharedRes"
}

