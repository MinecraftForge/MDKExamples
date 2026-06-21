# AccessTransformers-only examples
These examples show you how to set up and use Forge's AccessTransformer framework in your mod with ForgeGradle.

AccessTransformers are a way of changing the access flags of existing fields and methods at classloading time, allowing
you to strip `final` from fields in Minecraft code, call otherwise private super constructors, read private fields and more.

Examples are provided for both Gradle Groovy DSL (fg7 folder) and Gradle Kotlin DSL (fg7-kotlin folder).

Remember that these are examples that show you how to use AccessTransformers only. Do **not** assume that using ATs
somehow prevents you from using them alongside other features or that the Minecraft version used in these examples is
the only one they work on, as that's not the case.

## Testing the examples
The logs should mention "Able to access a private field" when running the `runClient` or `runServer` Gradle tasks. This
is logged from the Minecraft class' logger which is made public by an example AccessTransformer entry and called from
the mod's constructor.

## Minimal usage
First, add an accesstransformer.cfg file inside the src/main/resources/META-INF/ folder and add your entries in that
file.

Then add `accessTransformer = true` (Groovy) or `useDefaultAccessTransformer()` (Kotlin) inside the `minecraft` block of
your buildscript.

Finally, add it to your mods.toml. The path in the mods.toml should omit the resources part as the string provided here
is resolved relative to that folder already. (i.e. use `"META-INF/accesstransformer.cfg"` ✅, *not*
`"resources/META-INF/accesstransformer.cfg"` nor `"src/main/resources/META-INF/accesstransformer.cfg"`).

## Multiple AT cfg files
It may be convenient to have multiple AccessTransformer config files depending on your setup, such as in multi-project
environments where you may have a separate AT config for your common code and an additional one for subproject-specific
entries.

Instead of `accessTransformer = true`/`useDefaultAccessTransformer()`, you can specify multiple file paths with:
`accessTransformers = files("src/main/resources/META-INF/common-accesstransformer.cfg", "src/main/resources/META-INF/subproject-accesstransformer.cfg")`

Remember to also update your mods.toml accordingly to point to the correct files. Multiple files can be specified as a
list in the toml. Also, bear in mind that your built production jar should contain all AT cfg files.

## Version support
Older Forge builds do not support specifying multiple AT files or an AT file that differs from the default path. You
must use a single file with `accessTransformer = true` and `useDefaultAccessTransformer()` on these older build[changelog.txt](../../../../../AppData/Local/Temp/changelog.txt)s.

It's strongly recommended to use these Forge versions or ideally newer to avoid these legacy limitations:

| MC version | Forge build |
|------------|-------------|
| 26.1+      | Any         |
| 1.21.11    | 61.0.10+    |
| 1.21.10    | 60.1.9+     |
| 1.21.8     | 58.1.18+    |
| 1.21.5     | 55.1.10+    |
| 1.21.4     | 54.1.16+    |
| 1.21.3     | 53.1.10+    |
| 1.21.1     | 52.1.14+    |
| 1.20.6     | 50.2.8+     |
| 1.20.4     | 49.2.7+     |
| 1.20.1     | 47.4.20+    |
| 1.19.4-    | None yet    |

All Forge builds for MC 26.1 and newer support specifying multiple AT files. No Forge builds for MC 1.19.4 or older
support the newer features yet, but we're open to PRs.

Older Forge builds do support AccessTransformers, but they must all be specified in a single file in the default
location that cannot be changed (`src/main/resources/META-INF/accesstransformer.cfg`).
