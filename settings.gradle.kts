pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven(uri("https://api.xposed.info/"))
        maven(uri("https://www.jitpack.io/"))
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(uri("https://api.xposed.info/"))
        maven(uri("https://www.jitpack.io/"))
    }
}

rootProject.name = "HyperAod"
include(":app")
