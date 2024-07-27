import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import ua.railian.gradle.configureTargets

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    explicitApi = ExplicitApiMode.Strict

    configureTargets()

    sourceSets {
        commonMain.dependencies {
            api(projects.lib.base.combine)
            api(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
