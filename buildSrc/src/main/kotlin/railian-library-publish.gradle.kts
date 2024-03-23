import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.`maven-publish`
import org.gradle.kotlin.dsl.signing
import ua.railian.gradle.Version
import java.util.*

plugins {
    `maven-publish`
    signing
}
