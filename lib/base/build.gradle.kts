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
            api(projects.lib.base.core)
            api(projects.lib.base.combine)
            api(projects.lib.base.combineFlows)
            api(projects.lib.base.collections)
            api(projects.lib.base.coroutines)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

configurePublishing(
    modifyArtifactId = { replace(oldValue = "base", newValue = "data-result") },
)
