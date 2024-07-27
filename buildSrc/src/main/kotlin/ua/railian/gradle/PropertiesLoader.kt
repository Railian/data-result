package ua.railian.gradle

import org.gradle.api.Project
import java.io.File
import java.util.Properties

/**
 * Extension function to load properties from a specified file into the project's extra properties.
 *
 * @param fileName The name of the properties file to load. Defaults to "local.properties".
 */
fun Project.loadLocalProperties(fileName: String = "local.properties") {
    // Define the properties file
    val file = rootProject.file(fileName).takeIf(File::exists) ?: return
    // Load properties from the file and add them to the project's extra properties
    Properties().apply { load(file.reader()) }.forEach { key, value ->
        // Add each property to the project's extra properties
        extensions.extraProperties[key.toString()] = value
    }
}
