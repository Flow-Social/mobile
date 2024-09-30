pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "Flow"

include(":app")

include(":core:api")
include(":core:database")
include(":core:data")
include(":core:uikit")
include(":core:domain")
include(":core:mock")
include(":core:auth")

include(":feature:feed")
include(":feature:chats")
include(":feature:explore")
include(":feature:login")
include(":feature:profile")
