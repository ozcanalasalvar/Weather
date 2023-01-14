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

        register("androidCompose") {
            id = "weather.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

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

        register("androidLibraryCompose") {
            id = "weather.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }


        register("androidHilt") {
            id = "weather.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidFeature") {
            id = "weather.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}