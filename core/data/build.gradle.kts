plugins {
    id("weather.android.library")
    id("weather.android.hilt")
}

android {
        namespace= "weather.core.data"
}

dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    implementation(libs.android.gms.play.services.location)

    testImplementation(project(":core:testing"))
}