plugins {
    id("brushai.android.library")
    id("brushai.android.hilt")
}

android {
    namespace = "com.vproject.brushai.core.datastore.test"

}

dependencies {
    api(project(":core:datastore"))
    api(libs.androidx.datastore.core)

    implementation(libs.protobuf.kotlin.lite)
    implementation(project(":core:common"))
    implementation(project(":core:testing"))
}
