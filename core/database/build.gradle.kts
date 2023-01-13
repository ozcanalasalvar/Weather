plugins {
    id("weather.android.library")
    id("weather.android.room")
    id("weather.android.hilt")
}

android {
    namespace = "weather.core.database"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.kotlinx.serialization.json)
}