// enables project modules dependency accessors (use it instead of string representations)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("convention-plugins/base")
    repositories {
        google {
            content {
                // path restrictions for faster dependency syncing
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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
include(":core:model")
include(":core:network")
include(":features:account")
include(":features:article")
include(":features:bookmarks")
include(":features:favorites")
include(":features:profile")
include(":features:sources")
include(":features:topheadlines")
include(":features:welcome")
include(":baselineprofile")
