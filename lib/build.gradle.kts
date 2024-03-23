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
version = Version(name = "0.2.2")

kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

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
            artifact(javadocJar)
            artifactId = githubRepositoryName
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
