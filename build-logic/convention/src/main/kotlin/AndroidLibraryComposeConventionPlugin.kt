import com.android.build.api.dsl.LibraryExtension
import com.ozcan.alasalvar.weather.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposeConventionPlugin  : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }

//            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
//            configurations.configureEach {
//                resolutionStrategy {
//                    force(libs.findDependency("junit4").get())
//                    // Temporary workaround for https://issuetracker.google.com/174733673
//                }
//            }
//            dependencies {
//                add("androidTestImplementation", kotlin("test"))
//                add("testImplementation", kotlin("test"))
//            }
        }
    }
}