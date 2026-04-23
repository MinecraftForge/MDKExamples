This is a internal project that collects all the other projects together into a multi-project build.
This is used for automated tests, this is NOT meant as an example for modders to consume.

The 'minecraft' sub directory is a bulk test for a bunch of minecraft versions
You can run `gradlew testRun` in that directory which will attempt to run the dedicated server and detect that a mod was loaded/executed correctly.