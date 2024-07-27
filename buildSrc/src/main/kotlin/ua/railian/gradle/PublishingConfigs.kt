package ua.railian.gradle

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.Sign
import org.gradle.plugins.signing.SigningExtension

/**
 * Configures the project for library publishing.
 *
 * @param groupId The group ID of the Maven publication.
 * @param modifyArtifactId A lambda function to modify the artifact ID.
 */
fun Project.configurePublishing(
    groupId: String = project.rootProject.group.toString(),
    modifyArtifactId: String.() -> String = { this },
) {
    // Apply necessary plugins for publishing
    plugins.apply("org.jetbrains.dokka")  // Dokka plugin for generating documentation
    plugins.apply("maven-publish")        // Maven Publish plugin for publishing artifacts
    plugins.apply("signing")              // Signing plugin for signing publications

    // Define a task for generating Javadoc JAR
    val javadocJar by tasks.register<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")  // Set the classifier to 'javadoc'
        dependsOn(tasks.named("dokkaHtml"))  // Ensure Dokka HTML task is completed before
    }

    // Configure the PublishingExtension to specify repository details and publications
    extensions.configure(PublishingExtension::class.java) {
        repositories {
            maven {
                name = "Sonatype"  // Define the repository name
                url = when (Version.from(version).isSnapshot) {
                    true -> "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    else -> "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                }.let(::uri)  // Set the repository URL based on whether the version is a snapshot
                credentials {
                    val sonatypeUsername: String by project
                    val sonatypePassword: String by project
                    username = sonatypeUsername  // Set the Sonatype username
                    password = sonatypePassword  // Set the Sonatype password
                }
            }
        }

        publications {
            withType<MavenPublication> {
                artifact(javadocJar)  // Attach the Javadoc JAR to the publication
                this.groupId = groupId  // Set the group ID for the publication
                artifactId = modifyArtifactId(artifactId)  // Modify the artifact ID
                configurePom(githubRepositoryName = "data-result")
            }
        }
    }

    // Configure the SigningExtension to sign the publications
    extensions.configure<SigningExtension> {
        sign(extensions.getByType<PublishingExtension>().publications)
    }

    // Ensure the sign task runs before publishing
    tasks.withType<AbstractPublishToMaven>().configureEach {
        dependsOn(tasks.withType<Sign>())
    }
}

/**
 * Configures the POM (Project Object Model) metadata for the Maven publication.
 *
 * @param githubRepositoryName The name of the GitHub repository.
 */
private fun MavenPublication.configurePom(
    githubRepositoryName: String,
) {
    pom {
        // Set the name of the project in the format: groupId:artifactId
        name.set("$groupId:$artifactId")

        // Set the URL to the GitHub repository
        url.set("https://github.com/Railian/$githubRepositoryName")

        // Set a description for the project
        description.set(
            """DataResult is a discriminated union that encapsulates a successful outcome 
            with a value of type [T] or a failure with an error of type [E].""".trimIndent()
        )

        // Define the license for the project
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        // Define the source control management (SCM) information
        scm {
            connection.set("https://github.com/Railian/$githubRepositoryName.git")
            developerConnection.set("git@github.com:Railian/$githubRepositoryName.git")
            url.set("https://github.com/Railian/$githubRepositoryName")
        }

        // Add developer information
        developers {
            developer {
                id.set("YevhenRailian")
                name.set("Yevhen Railian")
                email.set("yevhen.railian.v@gmail.com")
            }
        }
    }
}
