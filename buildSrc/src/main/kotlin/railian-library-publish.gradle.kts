import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.`maven-publish`
import org.gradle.kotlin.dsl.signing
import ua.railian.gradle.Version
import java.util.*

plugins {
    `maven-publish`
    signing
}

private val githubRepositoryName = "data-result"

Properties().apply { load(project.rootProject.file("local.properties").reader()) }
    .onEach { project.ext[it.key.toString()] = it.value }

val javadocJar = tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
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