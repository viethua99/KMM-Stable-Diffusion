plugins {
    id("texttoimage.android.library")
    id("texttoimage.android.hilt")
}

android {
    namespace = "com.vproject.texttoimage.core.datastore.test"

}

dependencies {
    api(project(":core:datastore"))
    api(libs.androidx.datastore.core)

    implementation(libs.protobuf.kotlin.lite)
    implementation(project(":core:common"))
    implementation(project(":core:testing"))
}
