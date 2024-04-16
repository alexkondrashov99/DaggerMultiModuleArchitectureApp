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

include(":feature2:presentation")
include(":feature2:impl")
include(":feature2:api")
include(":feature2:domain")
include(":feature2:data")
include(":dynamicFeatureNavigation")
