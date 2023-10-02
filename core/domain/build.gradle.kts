plugins {
    id("weather.android.library")
    id("weather.android.hilt")
}

android {
    namespace = "weather.core.domain"

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))

}