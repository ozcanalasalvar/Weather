import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

@Suppress("UnstableApiUsage")
class AndroidRoomConventionPlugin: Plugin<Project> {
    override fun apply(target: org.gradle.api.Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.kapt")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(libs.findDependency("room-ktx").get())
                "implementation"(libs.findDependency("room-runtime").get())
                "kapt"(libs.findDependency("room-compiler").get())
            }
        }
    }

}