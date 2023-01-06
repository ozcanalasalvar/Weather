plugins {
    id("weather.android.application")
    id("weather.android.hilt")
}

android {

    defaultConfig {
        applicationId = "com.ozcan.alasalvar.weather"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    //implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui.core)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.lifecycle.viewmodel)
    implementation(libs.androidx.activity.compose)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.compose.ui.util)

    //ViewPager
    implementation(libs.com.google.accompanist.pager)
    implementation(libs.com.google.accompanist.pager.indicators)

    //Glide
    implementation(libs.glide.compose)
    //constraintlayout
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.navigation.compose)

    // Dagger & Hilt
//    implementation(libs.hilt.android)
//    implementation(libs.hilt.navigation.compose)
//    kapt(libs.hilt.android.compiler)



    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
}

kapt {
    correctErrorTypes = true
}

