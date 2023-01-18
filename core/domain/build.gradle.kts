plugins {
    id("weather.android.library")
    id("weather.android.hilt")
}

android {
    namespace = "weather.core.domain"
}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

}