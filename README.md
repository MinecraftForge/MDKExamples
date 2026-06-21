# MDK Examples

A collection of example Mod Developer Kits (MDKs) ranging from simple to complex. Examples are provided for both Gradle
Groovy DSL (fg7 folder) and Gradle Kotlin DSL (fg7-kotlin folder).

> [!IMPORTANT]
> Remember that these are mostly minimal examples that show you how to use specific features. Do **not** assume that
using a given feature somehow prevents you from using them alongside other features or that the Minecraft version used
in these examples is the only one they work on, as that's not the case.

## Index
- [AccessTransformers-only]: shows you how to set up and use Forge's AccessTransformer framework in your mod with
ForgeGradle, including multiple AT files and custom paths.
- [JarJar-only]: demonstrates how to use Forge's JarJar to bundle dependencies into your mod's jar, an alternative to
[GradleUp Shadow].
- [Obfuscation]: shows how to set up Forge's [Renamer] so that your production mod's jar is obfuscated, a required step
for legacy Minecraft versions (1.20.4 and older). Should *not* be done on newer MC versions.
- [Mixins-only]: a minimal example that shows how to set up [Mixin], a popular coremodding framework
- [Mixins w/ Facade interface injection]: shows how to use Forge's [Facade] to inject interfaces into existing classes
at compile-time alongside Mixin to handle runtime.
- [Mixins w/ obfuscation]: demonstrates how to configure Forge's Renamer plugin to get Mixin working on obfuscated versions.
- [Traditional MDK]: the "fat" MDK from the ForgeGradle 6- era, mainly intended as a reference to help migrate from FG6 to FG7.

[AccessTransformers-only]: https://github.com/MinecraftForge/MDKExamples/tree/master/accesstransformers-only
[JarJar-only]: https://github.com/MinecraftForge/MDKExamples/tree/master/jarjar-only
[Obfuscation]: https://github.com/MinecraftForge/MDKExamples/tree/master/obfuscation
[Mixins-only]: https://github.com/MinecraftForge/MDKExamples/tree/master/mixins-only
[Mixins w/ Facade interface injection]: https://github.com/MinecraftForge/MDKExamples/tree/master/mixins-facade-interface-injection
[Mixins w/ obfuscation]: https://github.com/MinecraftForge/MDKExamples/tree/master/mixins-obfuscation
[Traditional MDK]: https://github.com/MinecraftForge/MDKExamples/tree/master/traditional-mdk

[GradleUp Shadow]: https://plugins.gradle.org/plugin/com.gradleup.shadow
[Renamer]: https://github.com/MinecraftForge/Renamer
[Mixin]: https://github.com/SpongePowered/Mixin
[Facade]: https://github.com/MinecraftForge/Facade
