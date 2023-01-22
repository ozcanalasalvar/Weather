import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("weather.android.library")
    id("weather.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "weather.core.network"
    defaultConfig {
        buildConfigField("String", "API_KEY", getApiKey() ?: "")
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.retrofit.kotlin.serialization)
}

fun getApiKey(): String? {
    return gradleLocalProperties(rootDir)["API_KEY"] as String?
}