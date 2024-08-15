import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "dev.jokku.aggregate"
    compileSdk = libs.versions.androidSdk.compile.get().toInt()

    defaultConfig {
        applicationId = "dev.jokku.aggregate"
        minSdkVersion(libs.versions.androidSdk.min.get().toInt())
        targetSdkVersion(libs.versions.androidSdk.target.get().toInt())
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // setting field for generated BuildConfig class
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

room {
    schemaDirectory("${rootProject.projectDir}/schemas")
}

dependencies {
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(libs.androidx.core.ktx)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.tracing:tracing-ktx:1.1.0")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.ui:ui-tooling-data")
    implementation("androidx.compose.ui:ui-util")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.6.0")
    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")
    // Pager, indicators, system ui controller
    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.1")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation("com.google.accompanist:accompanist-webview:0.30.1")
    // DataStore preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    // Hilt DI
    implementation("com.google.dagger:hilt-android:2.47")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("com.google.dagger:hilt-compiler:2.47")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    // Room
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    // Google Play Services
    implementation("com.google.android.gms:play-services-auth:20.6.0")
    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    // KTor
    val ktorVersion = "2.3.2"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:1.4.8")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.06.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}