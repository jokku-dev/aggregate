import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "dev.aggregate.conventionplugins.base"

dependencies {
    implementation(libs.gradleplugin.android)
    implementation(libs.gradleplugin.compose)
    implementation(libs.gradleplugin.composeCompiler)
    implementation(libs.gradleplugin.kotlin)

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.java.get())

java {
    sourceCompatibility = projectJavaVersion
    targetCompatibility = projectJavaVersion
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
}
