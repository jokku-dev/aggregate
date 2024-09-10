pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Aggregate"
include(":app")
include(":core:common")
include(":core:database")
include(":core:datastore")
include(":core:data")
include(":core:designsystem")
include(":core:network")
include(":core:ui")
include(":features:account")
include(":features:article")
include(":features:bookmarks")
include(":features:favorites")
include(":features:profile")
include(":features:sources")
include(":features:topheadlines")
include(":features:welcome")
include(":core:sync")
include(":core:model")
include(":baselineprofile")
