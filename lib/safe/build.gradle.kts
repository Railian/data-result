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
            api(projects.lib.safe.core)
            api(projects.lib.safe.combine)
            api(projects.lib.safe.combineFlows)
            api(projects.lib.safe.collections)
            api(projects.lib.safe.coroutines)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

configurePublishing(
    modifyArtifactId = { "data-result-$this" },
)
