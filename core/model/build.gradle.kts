@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("kotlin")
    id("kotlinx-serialization")

}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}