import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "dev.jokku.aggregate"
    compileSdk = libs.versions.androidSdk.compile.get().toInt()

    defaultConfig {
        applicationId = "dev.jokku.aggregate"
        minSdk = libs.versions.androidSdk.min.get().toInt()
        targetSdk = libs.versions.androidSdk.target.get().toInt()
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
        buildConfigField("String", "API_BASE_URL", "\"${properties.getProperty("API_BASE_URL")}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

/*room {
    schemaDirectory("${rootProject.projectDir}/schemas")
}*/

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.profileinstaller)
    kapt(libs.dagger.hilt.compiler)

    implementation(project(":core:newsdata"))
    implementation(project(":core:newsapi"))
    implementation(project(":core:newsdb"))
    implementation(project(":features:topheadlines"))
//    implementation(projects.core.common)
//    implementation(projects.core.uikit)

    debugImplementation(libs.okhttp.logging.interceptor)

//    baselineProfile(projects.baselineprofile)



//    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
//    implementation("androidx.tracing:tracing-ktx:1.1.0")
//    implementation("androidx.work:work-runtime-ktx:2.8.1")
    // Compose BOM
//    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.compose.material3:material3-window-size-class")
//    implementation("androidx.compose.ui:ui-tooling-data")
//    implementation("androidx.compose.ui:ui-util")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    debugImplementation("androidx.compose.ui:ui-tooling")
    // Navigation for Compose
//    implementation("androidx.navigation:navigation-compose:2.6.0")
    // Splash API
//    implementation("androidx.core:core-splashscreen:1.0.1")
    // Pager, indicators, system ui controller
//    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.1")
//    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
//    implementation("com.google.accompanist:accompanist-webview:0.30.1")
    // DataStore preferences
//    implementation("androidx.datastore:datastore-preferences:1.0.0")
//    implementation("androidx.datastore:datastore:1.0.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    // Hilt DI
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
//    implementation("androidx.hilt:hilt-work:1.0.0")
//    kapt("androidx.hilt:hilt-compiler:1.0.0")
    // Google Play Services
//    implementation("com.google.android.gms:play-services-auth:20.6.0")
    // Coil
//    implementation("io.coil-kt:coil-compose:2.4.0")
    // KTor
//    val ktorVersion = "2.3.2"
//    implementation("io.ktor:ktor-client-core:$ktorVersion")
//    implementation("io.ktor:ktor-client-android:$ktorVersion")
//    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
//    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
//    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
//    implementation("io.ktor:ktor-client-logging:$ktorVersion")
//    implementation("ch.qos.logback:logback-classic:1.4.8")

//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    androidTestImplementation(platform("androidx.compose:compose-bom:2023.06.01"))
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}