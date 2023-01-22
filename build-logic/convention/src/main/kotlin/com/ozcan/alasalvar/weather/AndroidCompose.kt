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
            "implementation"(libs.findDependency("androidx-compose-ui-core").get())
            "implementation"(libs.findDependency("androidx-compose-material").get())
            "implementation"(libs.findDependency("androidx-compose-ui.tooling-preview").get())
            "implementation"(libs.findDependency("androidx-compose-lifecycle-viewmodel").get())
            "implementation"(libs.findDependency("androidx-lifecycle-runtimeCompose").get())
            "implementation"(libs.findDependency("androidx-activity-compose").get())


            "debugImplementation"(libs.findDependency("androidx.compose.ui.tooling").get())
            "implementation"(libs.findDependency("androidx.compose.ui.util").get())

            //ViewPager
            "implementation"(libs.findDependency("com.google.accompanist.pager").get())
            "implementation"(libs.findDependency("com.google.accompanist.pager.indicators").get())

            //Glide
            "implementation"(libs.findDependency("glide.compose").get())
            //constraintlayout
            "implementation"(libs.findDependency("androidx.constraintlayout.compose").get())
            "implementation"(libs.findDependency("androidx.navigation.compose").get())

        }

    }

}
