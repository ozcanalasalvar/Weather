import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import com.ozcan.alasalvar.weather.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType


class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
           // pluginManager.apply("com.android.library")
            extensions.configure<ApplicationExtension>{
                configureAndroidCompose(this)
            }

        }
    }

}