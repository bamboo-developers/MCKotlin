pluginManagement {
    val kotlinVersion = providers.gradleProperty("kotlin_version")

    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
    }
}

rootProject.name = "MCKotlin"
