plugins {
    id("java")
    id("idea")
    id("eclipse")
    id("maven-publish")
    id("net.minecraftforge.gradle") version "[7.0.29,8.0)"
}

val minecraft_version: String = providers.gradleProperty("minecraft_version").get()
val forge_version: String = providers.gradleProperty("forge_version").get()
val mod_id: String = providers.gradleProperty("mod_id").get()

version = "1.0"
group = "net.minecraftforge"
base.archivesName = mod_id

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

minecraft {
    // Tell FG7 Facade where to find the config file which specifies which classes to inject interfaces onto, and which
    // interfaces to inject at setup time. You can optionally specify multiple files. Runtime needs to be implemented
    // separately, such as with a Mixin.
    facade = files("src/main/resources/${mod_id}.facade.cfg")

    runs {
        configureEach {
            workingDir.convention(layout.projectDirectory.dir("run"))
            // Mixin requires either specifying the config via command line, or in the Manifest
            args("--mixin.config=${mod_id}.mixins.json")
        }
        register("client") {
            systemProperty("forge.enabledGameTestNamespaces", mod_id)
        }
    }
}

repositories {
    minecraft.mavenizer(this)
    maven(fg.forgeMaven)
    maven(fg.minecraftLibsMaven)
    mavenCentral()
}

dependencies {
    implementation(minecraft.dependency("net.minecraftforge:forge:$minecraft_version-$forge_version"))
    annotationProcessor("org.spongepowered:mixin:0.8.7:processor")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8" // Use the UTF-8 charset for Java compilation
}

tasks.named<Jar>("jar") {
    manifest {
        // Add our config to the manifest of our jar to have Mixin detect us at runtime.
        attributes["MixinConfigs"] = "${mod_id}.mixins.json"
    }
}
