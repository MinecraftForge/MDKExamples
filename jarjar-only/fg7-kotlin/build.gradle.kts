import net.minecraftforge.jarjar.gradle.JarJarDependencyMethods

plugins {
    id("java")
    id("idea")
    id("eclipse")
    id("maven-publish")
    id("net.minecraftforge.gradle") version "[7.0.29,8.0)"
    id("net.minecraftforge.jarjar") version "0.2.3"
}

val minecraft_version: String = providers.gradleProperty("minecraft_version").get()
val forge_version: String = providers.gradleProperty("forge_version").get()
val mod_id: String = providers.gradleProperty("mod_id").get()

version = "1.0"
group = "net.minecraftforge"
base.archivesName = mod_id

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

minecraft {
    runs.register("client")
}

repositories {
    minecraft.mavenizer(this)
    maven(fg.forgeMaven)
    maven(fg.minecraftLibsMaven)
    mavenCentral()
}

// Creates the jarJar configuration.
// This creates the 'jarJar' task, which by default has a classifier of 'all', with a base of the 'jar' task
// For more info see https://github.com/MinecraftForge/JarJar/blob/main/jarjar-gradle/src/main/java/net/minecraftforge/jarjar/gradle/JarJarExtension.java
jarJar.register() {
    archiveClassifier = null
}

// If you want to have your "normal" jar the 'all' jar, you can change the classifer using normal gradle configuration
tasks.named<Jar>("jar") {
    archiveClassifier = "slim"
}

dependencies {
    implementation(minecraft.dependency("net.minecraftforge:forge:$minecraft_version-$forge_version"))

    "jarJar"("org.apache.maven:maven-artifact:3.9.11") {
        jarJar.configure(this) {
            // This tells that we do NOT ship this library, but we depend on a specific version
            // For more info see https://github.com/MinecraftForge/JarJar/blob/main/jarjar-gradle/src/main/java/net/minecraftforge/jarjar/gradle/JarJarMetadataInfo.java
            setConstraint(true)
            setVersion("3.9.11")
        }
    }
    
    // This will bundle the jar and set the constraints to [0.5.0,)
    "jarJar"("io.github.llamalad7:mixinextras-forge:0.5.0")
}
