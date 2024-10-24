plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    id("android.base.config")
}

android {
    namespace = "dev.aggregate.datastore"

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore.core.android)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.dagger.hilt.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.material)
    kapt(libs.dagger.hilt.compiler)
    implementation(projects.core.model)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
