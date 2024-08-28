plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.dagger.hilt.android)
    implementation(libs.kotlinx.coroutines.core)
}