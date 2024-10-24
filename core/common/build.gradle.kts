plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    id("android.base.config")
}

android {
    namespace = "dev.aggregate.common"

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.dagger.hilt.android)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
    kapt(libs.dagger.hilt.compiler)
}
