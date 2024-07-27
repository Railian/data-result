import ua.railian.gradle.Version
import ua.railian.gradle.configureDetekt
import ua.railian.gradle.loadLocalProperties

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.dokka) apply false
}

group = "io.github.railian.data"

subprojects {
    configureDetekt()
    loadLocalProperties()
    version = Version(
        name = "0.3.0-alpha05",
        isSnapshot = false,
    )
}
