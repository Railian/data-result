import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import ua.railian.gradle.configurePublishing
import ua.railian.gradle.configureTargets

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    explicitApi = ExplicitApiMode.Strict

    configureTargets()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.lib.safe.core)
            implementation(projects.lib.base.combine)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

configurePublishing(
    modifyArtifactId = { "data-result-safe-$this" },
)