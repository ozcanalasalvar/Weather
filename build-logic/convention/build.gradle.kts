plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "weather.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidRoom") {
            id = "weather.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("androidLibrary") {
            id = "weather.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidCompose") {
            id = "weather.android.application.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidHilt") {
            id = "weather.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}