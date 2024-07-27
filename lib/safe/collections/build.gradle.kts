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
            api(projects.lib.common.collections)
            implementation(projects.lib.common.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

configurePublishing(
    modifyArtifactId = { "data-result-safe-$this" },
)
