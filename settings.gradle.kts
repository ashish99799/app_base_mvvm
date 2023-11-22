pluginManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        jcenter()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Application Base"
include(":app_base")
