pluginManagement {
    includeBuild("build-logic")
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
rootProject.name = "BrushAI"
include(":app")
include(":feature:generate")
include(":feature:explore")
include(":core:network")
include(":core:database")
include(":core:data")
include(":core:model")
include(":core:domain")
include(":core:designsystem")
include(":core:ui")
include(":core:testing")
include(":ui-test-hilt-manifest")
include(":core:datastore")
include(":core:common")
