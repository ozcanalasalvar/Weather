package com.ozcan.alasalvar.weather

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("UnstableApiUsage")
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("compose").get().toString()
        }

        dependencies {
            "implementation"(libs.findLibrary("androidx-compose-ui-core").get())
            "implementation"(libs.findLibrary("androidx-compose-material").get())
            "implementation"(libs.findLibrary("androidx-compose-ui.tooling-preview").get())
            "implementation"(libs.findLibrary("androidx-compose-lifecycle-viewmodel").get())
            "implementation"(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
            "implementation"(libs.findLibrary("androidx-activity-compose").get())


            "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling").get())
            "implementation"(libs.findLibrary("androidx.compose.ui.util").get())

            //ViewPager
            "implementation"(libs.findLibrary("com.google.accompanist.pager").get())
            "implementation"(libs.findLibrary("com.google.accompanist.pager.indicators").get())

            //Glide
            "implementation"(libs.findLibrary("glide.compose").get())
            //constraintlayout
            "implementation"(libs.findLibrary("androidx.constraintlayout.compose").get())
            "implementation"(libs.findLibrary("androidx.navigation.compose").get())

        }

    }

}
