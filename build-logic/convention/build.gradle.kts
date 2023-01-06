plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "weather.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }


        register("androidHilt") {
            id = "weather.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidRoom") {
            id = "weather.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("androidLibrary") {
            id = "weather.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}