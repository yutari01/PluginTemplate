import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    kotlin("jvm") version "2.1.0"
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

group = property("group") as String
version = property("version") as String
description = property("description") as String
val mcVersion = property("mc_version") as String
val pluginName = property("pluginName") as String

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    val mcVersion = property("mc_version") as String
    val kotlinVersion = property("kt_version") as String

    compileOnly("io.papermc.paper:paper-api:$mcVersion-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
}

tasks.named<ShadowJar>("shadowJar") {
    archiveFileName.set("${project.name}_${project.version}.jar")
    archiveClassifier.set("")
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(
            "pluginName" to pluginName,
            "pluginVersion" to project.version,
            "pluginDescription" to project.description,
            "apiVersion" to mcVersion
        )
    }
}