import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm")
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

allprojects {
    group = "io.github.subkek"
    version = properties["plugin_version"]!!
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val spigotVersion = properties["spigot_version"]
val kotlinVersion = properties["kotlin_version"]

dependencies {
    compileOnly("org.spigotmc:spigot-api:$spigotVersion")

    shadow("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")
    shadow("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
}

bukkit {
    name = rootProject.name
    version = rootProject.version as String
    main = "io.github.subkek.mckotlin.MCKotlin"

    authors = listOf("subkek")
    website = "https://discord.gg/eRvwvmEXWz"
    apiVersion = "1.16"

    load = BukkitPluginDescription.PluginLoadOrder.STARTUP

    foliaSupported = true
}

tasks.jar { enabled = false }
tasks.build { dependsOn(tasks.shadowJar) }

tasks.shadowJar {
    archiveFileName = "${rootProject.name}-$version.jar"

    configurations = listOf(project.configurations.shadow.get())
    mergeServiceFiles()

    fun relocate(pkg: String) = relocate(pkg, "${rootProject.group}.mckotlin.libs.$pkg")
}
