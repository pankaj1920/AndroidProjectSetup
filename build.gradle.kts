plugins {
    id (CorePlugins.Application) version (Versions.Application) apply false
    id (CorePlugins.Library) version (Versions.Library) apply false
    id (CorePlugins.Android) version (Versions.Android) apply false

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}