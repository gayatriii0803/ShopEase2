pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Change to PREFER_SETTINGS if needed
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ShopEase2"
include(":app")
