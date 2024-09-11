import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    // apply means immediate application of plugin
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.androidx.baselineprofile) apply false
    alias(libs.plugins.androidx.room) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
}

// This task is time-consuming
allprojects.onEach { project ->
    project.afterEvaluate {
        // It's a part of configuration phase and executes after project evaluation
        with(project.plugins) {
            if (hasPlugin(libs.plugins.kotlin.android.get().pluginId) ||
                hasPlugin(libs.plugins.kotlin.jvm.get().pluginId)
            ) {
                apply(libs.plugins.detekt.get().pluginId)

                project.extensions.configure<DetektExtension> {
                    config.setFrom(rootProject.files("default-detekt-config.yml"))
                }

                project.dependencies.add("detektPlugins", libs.detekt.formatting.get().toString())
            }

            if (hasPlugin(libs.plugins.kotlin.compose.get().pluginId)) {
                project.dependencies.add(
                    "detektPlugins",
                    libs.detekt.rules.compose.get().toString()
                )
            }
        }
    }
}
