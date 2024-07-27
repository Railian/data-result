plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    gradlePluginPortal() // To use 'maven-publish' and 'signing' plugins in our own plugin
}

dependencies {
    implementation(gradleApi())
    val kotlinVersion = libs.versions.kotlin.get()
    implementation(kotlin("gradle-plugin", version = kotlinVersion))
    val detektVersion = libs.versions.detekt.get()
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
}
