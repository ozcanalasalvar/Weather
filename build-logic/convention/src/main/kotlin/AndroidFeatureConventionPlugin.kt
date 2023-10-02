import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

@Suppress("UnstableApiUsage")
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("weather.android.library")
                apply("weather.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:domain"))

                add("testImplementation", kotlin("test"))
                add("testImplementation", project(":core:testing"))
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", project(":core:testing"))

                "implementation"(libs.findDependency("hilt-android").get())

                "implementation"(libs.findDependency("hilt-navigation-compose").get())
                "implementation"(libs.findDependency("androidx.lifecycle.runtimeCompose").get())
                "implementation"(libs.findDependency("androidx.compose.lifecycle.viewmodel").get())

                "implementation"(libs.findDependency("kotlinx.coroutines.android").get())
            }
        }
    }
}