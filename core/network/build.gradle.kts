import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    id("android.base.config")
}

android {
    namespace = "dev.aggregate.network"

    defaultConfig {
        // setting field for generated BuildConfig class
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
        buildConfigField("String", "API_BASE_URL", "\"${properties.getProperty("API_BASE_URL")}\"")
    }

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.annotation)
    implementation(libs.dagger.hilt.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.adapters.result)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.retrofit.responseTypeKeeper)
    implementation(projects.core.model)
    kapt(libs.dagger.hilt.compiler)
}
