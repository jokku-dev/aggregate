buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version ("8.1.0-beta03") apply (false)
    id("com.android.library") version ("8.1.0-beta03") apply (false)
    id("com.google.dagger.hilt.android") version("2.46.1") apply(false)
    id("com.google.devtools.ksp") version("1.8.20-1.0.10") apply(false)
    kotlin("android") version("1.8.20") apply(false)
    kotlin("plugin.serialization") version("1.8.20") apply(false)
}