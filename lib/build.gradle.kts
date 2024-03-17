import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import ua.railian.gradle.Version

plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

group = "io.github.railian.data"
version = Version(name = "0.1.0")

kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    explicitApi = ExplicitApiMode.Strict
}

apply<RailianLibraryPublishPlugin>()
