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
include(":core:testing")
include(":feature:generate")
include(":core:designsystem")
include(":core:ui")
include(":ui-test-hilt-manifest")
include(":feature:explore")
include(":core:network")
include(":core:database")
include(":core:model")
