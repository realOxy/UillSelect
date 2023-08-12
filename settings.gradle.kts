rootProject.name = "UillSelect"

include(":androidApp")
include(":shared")
include(":desktopApp")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        mavenCentral()
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String
        val sqldelightVersion = extra["sqldelight.version"] as String
        val kspVersion = extra["ksp.version"] as String
        val ktorfitPluginVersion = extra["ktorfit.plugin.version"] as String

        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("android") version kotlinVersion

        id("com.android.application") version agpVersion
        id("com.android.library") version agpVersion

        id("org.jetbrains.compose") version composeVersion

        id("app.cash.sqldelight") version sqldelightVersion
        id("com.google.devtools.ksp") version kspVersion
        id("de.jensklingenberg.ktorfit") version ktorfitPluginVersion
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
