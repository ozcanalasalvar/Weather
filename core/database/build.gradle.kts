plugins {
    id("weather.android.library")
    id("weather.android.room")
    id("weather.android.hilt")
    id("kotlinx-serialization")
}

android {
    defaultConfig {
        testInstrumentationRunner =
            "com.ozcanalasalvar.core.testing.WeatherTestRunner"
    }
    namespace = "weather.core.database"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(libs.kotlinx.serialization.json)
    testImplementation("junit:junit:4.12")

    androidTestImplementation(project(":core:testing"))
}