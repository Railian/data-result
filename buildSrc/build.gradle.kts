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
//    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
//    implementation("org.jetbrains.dokka:dokka-core:1.9.20")
}
