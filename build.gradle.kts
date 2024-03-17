plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.kotlinMultiplatform) apply false
    `railian-library-publish` apply false
}
