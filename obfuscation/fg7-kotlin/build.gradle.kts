// TODO [MDKExamples] This Kotlin buildscript is still very experimental. I am very new to Kotlin
//      I welcome suggestions with open arms.

plugins {
    id("java")
    id("idea")
    id("eclipse")
    id("maven-publish")
    id("net.minecraftforge.gradle") version "[7.0.11,8.0)"
    id("net.minecraftforge.renamer") version "1.0.2"
}

val minecraft_version: String by project
val forge_version: String by project

version = "1.0"
group = "net.minecraftforge"
base.archivesName = "obfuscation"

java.toolchain.languageVersion = JavaLanguageVersion.of(17)

minecraft {
    mappings("official", "1.20.1")
}

repositories {
    minecraft.mavenizer(this)
    maven(fg.forgeMaven)
    maven(fg.minecraftLibsMaven)
}

dependencies {
    implementation(minecraft.dependency("net.minecraftforge:forge:1.20.1-47.4.0"))
}

// Creates a task named 'renameJar'
renamer.classes(tasks.named<Jar>("jar")) {
    // You need to point to the mappings you wish to apply, typically this is the Mapped names to SRG for older versions.
    // ForgeGradle/Mavenizer generate these files for the dependencies you declare. So you can use the helper.
    // Or you can specify the file or dependency if you host them yourself.
    map.from(minecraft.dependency.toSrgFile)
    // This is publishable task so you can specify things such as the classifier
    archiveClassifier = "srg"
}

// If you want to create another task, or customize the name you can specify it as the first argument
renamer.classes("renameJarToSrg", tasks.named<Jar>("jar")) {
    // This specifies the map via a dependency coordinate, such as 'net.minecraft:mappings_official:1.20.1-20230612.114412:map2srg@tsrg.gz'
    mappings(minecraft.dependency.toSrg.get())
}

publishing {
    repositories {
        maven { url = layout.projectDirectory.dir("repo").asFile.toURI() }
    }

    publications.register<MavenPublication>("mavenJava") {
        from(components["java"]) // Publish the normal jar
        artifact(tasks["renameJar"]) // Publish the renamed jar in addition
    }
}
