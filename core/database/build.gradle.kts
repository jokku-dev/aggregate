plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.jokku.database"
    compileSdk = libs.versions.androidSdk.compile.get().toInt()
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(project(":core:model"))
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
}