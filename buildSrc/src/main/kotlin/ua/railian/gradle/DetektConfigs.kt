package ua.railian.gradle

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Configures Detekt for the project.
 *
 * This function applies the Detekt plugin and sets up its configuration:
 * - Enables all rules.
 * - Sets the source directories to "src".
 * - Specifies the configuration file for Detekt.
 */
fun Project.configureDetekt() {
    // Apply the Detekt plugin
    plugins.apply("io.gitlab.arturbosch.detekt")

    // Configure Detekt extension
    extensions.configure<DetektExtension> {
        allRules = true  // Enable all rules
        source.from("src")  // Set the source directories
        config.from(file("$rootDir/detekt/config.yml"))  // Specify the configuration file
    }
}
