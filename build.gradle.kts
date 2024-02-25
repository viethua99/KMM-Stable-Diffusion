buildscript {
    dependencies {
        classpath(libs.moko.resources.generator)
    }
}

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.pluginSerialization) apply false
    alias(libs.plugins.buildKonfigPlugin) apply false
    alias(libs.plugins.icerockMobilePlugin) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.cocoapods) apply false
}