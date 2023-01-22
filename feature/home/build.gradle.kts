plugins {
    id("weather.android.feature")
    id("weather.android.library.compose")
}

android {
    namespace = "weather.feature.home"
}

dependencies {
    implementation(libs.glide.compose)
}