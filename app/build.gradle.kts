plugins {
    id("weather.android.application")
    id("weather.android.application.compose")
    id("weather.android.room")
    id("weather.android.hilt")
}

android {

    defaultConfig {
        applicationId = "com.ozcan.alasalvar.weather"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.ozcanalasalvar.testing.WeatherTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            //applicationIdSuffix = ".debug"
        }
        release {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
        }
    }


    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":feature:search"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:home"))

    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:testing"))

    implementation(libs.accompanist.permissions)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}

