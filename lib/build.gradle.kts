import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import ua.railian.gradle.Version
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.dokka)
    `maven-publish`
    signing
}

group = "io.github.railian.data"
version = Version(name = "0.3.0-alpha04", isSnapshot = true)

kotlin {

    jvm() // JVM target

    // JS targets
    js(IR) {
        browser() // web browsers
        nodejs()  // Node.js
    }

    wasmJs {
        browser() // web browsers
        nodejs() // Node.js
    }

    // iOS targets
    iosArm64() // iOS ARM64
    iosX64() // iOS x64
    iosSimulatorArm64() // iOS simulator ARM64

    // macOS targets
    macosX64() // macOS x64
    macosArm64() // macOS ARM64

    // Linux target
    linuxX64() // Linux x64

    // Windows target
    mingwX64() // Windows x64 (MinGW)

    // WatchOS targets
    watchosArm32() // WatchOS ARM32
    watchosArm64() // WatchOS ARM64
    watchosX64() // WatchOS x64
    watchosSimulatorArm64() // WatchOS simulator ARM64

    // tvOS targets
    tvosArm64() // tvOS ARM64
    tvosX64() // tvOS x64
    tvosSimulatorArm64() // tvOS simulator ARM64

    // Android targets
    androidNativeArm32() // Android ARM32
    androidNativeArm64() // Android ARM64
    androidNativeX86() // Android x86
    androidNativeX64() // Android x64

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    explicitApi = ExplicitApiMode.Strict
}

private val githubRepositoryName = "data-result"

val localProperties = project.rootProject.file("local.properties")
if (localProperties.exists()) {
    Properties().apply { load(localProperties.reader()) }
        .onEach { project.ext[it.key.toString()] = it.value }
}

val javadocJar = tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    dependsOn(tasks.dokkaHtml)
}

publishing {
    repositories {
        maven {
            name = "Sonatype"
            url = when (Version.from(version).isSnapshot) {
                true -> "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                else -> "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            }.let(::uri)
            credentials {
                username = project.ext["sonatypeUsername"].toString()
                password = project.ext["sonatypePassword"].toString()
            }
        }
    }

    publications {
        withType<MavenPublication> {
            artifactId = githubRepositoryName + artifactId.removePrefix("lib")

            pom {
                name.set("$groupId:$artifactId")
                url.set("https://github.com/Railian/$githubRepositoryName")
                description.set("Some coroutines extensions for easy codding")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                scm {
                    connection.set("https://github.com/Railian/$githubRepositoryName.git")
                    developerConnection.set("git@github.com:Railian/$githubRepositoryName.git")
                    url.set("https://github.com/Railian/$githubRepositoryName")
                }
                developers {
                    developer {
                        id.set("YevhenRailian")
                        name.set("Yevhen Railian")
                        email.set("yevhen.railian.v@gmail.com")
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn(tasks.withType<Sign>())
}
