enableFeaturePreview("VERSION_CATALOGS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Weather"
include(":app")
include(":core:data")
include(":core:database")
include(":core:network")
include(":core:model")
include(":feature:search")
include(":core:common")
