plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.androidx.baselineprofile)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "dev.aggregate.app"
    compileSdk = libs.versions.androidSdk.compile.get().toInt()

    defaultConfig {
        applicationId = "dev.aggregate.app"
        minSdk = libs.versions.androidSdk.min.get().toInt()
        targetSdk = libs.versions.androidSdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // removing of unsupported resources

        // supported string resources (to exclude default strings of other languages)
        resourceConfigurations += setOf("ru", "en")
        // supported cpu architectures (our app is only for mobile phones)
        ndk {
            // noinspection ChromeOsAbiSupport
            abiFilters += setOf("armeabi-v7a", "arm64-v8a")
        }
    }

    buildTypes {
        getByName("release") {
            // cuts unused code, keep in mind that compiled dex file can contain a maximum of 65536 functions
            // in case of using reflection or something else indirect
            // we should use keep annotations to retain that unused code
            isMinifyEnabled = true
            // also we have build tools such as:
            // code optimizer - to make possible suboptimal code algorithms faster
            // obfuscation - "entanglement" of code for security, it can be reverse engineered
            // but at least it makes code more concise
            // verification - verifies compatibility of code with different versions of language
            // but in android it's out of the box
            proguardFiles(
                // default file from Android SDK
                getDefaultProguardFile("proguard-android-optimize.txt"),
                // our project file
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
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/okhttp3/internal/publicsuffix/NOTICE"
            // mainly reflect annotations
            excludes += "/kotlin/**"
            // versions of libraries
            excludes += "META-INF/androidx.*.version"
            excludes += "META-INF/com.google.*.version"
            excludes += "META-INF/kotlinx_*.version"
            excludes += "META-INF/com/android/build/gradle/*"

            excludes += "kotlin-tooling-metadata.json"
            excludes += "DebugProbesKt.bin"
        }
    }
}

/*room {
    schemaDirectory("${rootProject.projectDir}/schemas")
}*/

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.profileinstaller)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3.android)
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)

    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))
    implementation(project(":features:topheadlines"))
    implementation(project(":features:account"))
    implementation(project(":features:bookmarks"))
    implementation(project(":features:favorites"))
    implementation(project(":features:article"))
    implementation(project(":features:profile"))
    implementation(project(":features:sources"))
    implementation(project(":features:welcome"))

    debugImplementation(libs.okhttp.logging.interceptor)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
