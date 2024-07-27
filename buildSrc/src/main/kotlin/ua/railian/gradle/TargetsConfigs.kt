package ua.railian.gradle

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

/**
 * Configures the Kotlin Multiplatform library project with various targets including
 * JVM, Android, iOS, WatchOS, tvOS, macOS, Linux, Windows, JS and WebAssembly.
 */
@OptIn(
    ExperimentalKotlinGradlePluginApi::class,
    ExperimentalWasmDsl::class,
)
fun KotlinMultiplatformExtension.configureTargets() {
    // JVM target
    jvm {
        compilerOptions {
            // Set the JVM target version to 1.8
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    // Android targets
    androidNativeArm32() // Android ARM32
    androidNativeArm64() // Android ARM64
    androidNativeX86() // Android x86
    androidNativeX64() // Android x64

    // iOS targets
    iosArm64() // iOS ARM64
    iosX64() // iOS x64
    iosSimulatorArm64() // iOS simulator ARM64

    // WatchOS targets
    watchosArm32() // WatchOS ARM32
    watchosArm64() // WatchOS ARM64
    watchosX64() // WatchOS x64
    watchosDeviceArm64() // WatchOS device ARM64
    watchosSimulatorArm64() // WatchOS simulator ARM64

    // tvOS targets
    tvosArm64() // tvOS ARM64
    tvosX64() // tvOS x64
    tvosSimulatorArm64() // tvOS simulator ARM64

    // macOS targets
    macosArm64() // macOS ARM64
    macosX64() // macOS x64

    // Linux target
    linuxArm64() // Linux ARM64
    linuxX64() // Linux x64

    // Windows target
    mingwX64() // Windows x64 (MinGW)

    // JavaScript (JS) targets
    js(IR) {
        browser() // web browsers
        nodejs()  // Node.js
    }

    // WebAssembly (Wasm) JS targets
    wasmJs {
        browser() // web browsers
        nodejs() // Node.js
    }
}
