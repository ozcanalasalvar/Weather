plugins {
    id("weather.android.library")
    id("kotlinx-serialization")
}

android {
    namespace = "weather.core.network"
}

dependencies {

    implementation(libs.retrofit.core)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.retrofit.kotlin.serialization)
}