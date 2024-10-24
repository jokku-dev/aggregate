plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
    id("android.base.config")
}

android {
    namespace = "dev.aggregate.database"

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.room.ktx)
    implementation(libs.dagger.hilt.android)
    ksp(libs.androidx.room.compiler)
    kapt(libs.dagger.hilt.compiler)
    implementation(projects.core.model)
}
