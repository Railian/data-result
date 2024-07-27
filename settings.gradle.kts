enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "data-result"

include(":lib:common:core")
include(":lib:common:combine-flows")
include(":lib:common:collections")
include(":lib:common:coroutines")

include(":lib:base")
include(":lib:base:core")
include(":lib:base:combine")
include(":lib:base:combine-flows")
include(":lib:base:collections")
include(":lib:base:coroutines")

include(":lib:safe")
include(":lib:safe:core")
include(":lib:safe:combine")
include(":lib:safe:combine-flows")
include(":lib:safe:collections")
include(":lib:safe:coroutines")
