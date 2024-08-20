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
include(":core:newsapi")
include(":core:newsdb")
include(":core:newsdata")
include(":core:designsystem")
include(":core:ui")
include(":features:topheadlines")
include(":features:account")
include(":features:bookmarks")
include(":features:favorites")
include(":features:article")
include(":features:profile")
include(":features:sources")
include(":features:welcome")