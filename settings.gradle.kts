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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WaifuApp"
include(":app")
include(":core")
include(":feature")
include(":feature:auth")
include(":feature:auth:api")
include(":feature:auth:impl")
include(":feature:register")
include(":core:data")
include(":core:data:impl")
include(":core:data:impl:firebase")
include(":core:data:api")
include(":core:common")
include(":core:systemdesign")
include(":feature:register:api")
include(":feature:register:impl")
include(":feature:profile:api")
include(":feature:profile:impl")
