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

rootProject.name = "DynamicFeatureApp"
include(":app")
include(":dynamicfeature1")
include(":Core")
include(":dashboard")
include(":feature1:presentation")
include(":feature1:impl")
include(":feature1:api")
include(":feature1:domain")
include(":feature1:data")
