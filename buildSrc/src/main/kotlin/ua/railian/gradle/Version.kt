package ua.railian.gradle

/**
 * Data class representing a version with a name and an optional snapshot indicator.
 *
 * @property name The name of the version.
 * @property isSnapshot Indicates whether the version is a snapshot.
 */
data class Version(
    val name: String,
    val isSnapshot: Boolean = false,
) {

    // A private property to build the full version name
    // including the snapshot suffix if applicable
    private val fullName = buildString {
        append(name)
        if (isSnapshot) append(SNAPSHOT_SUFFIX)
    }

    // Overrides the default toString method
    // to return the full version name
    override fun toString(): String = fullName

    companion object {

        private const val SNAPSHOT_SUFFIX = "-SNAPSHOT"

        /**
         * Factory method to create a [Version] instance from any object.
         *
         * @param version The input object to convert into a [Version] instance.
         * @return A [Version] instance based on the input.
         */
        fun from(version: Any): Version {
            // Attempt to cast the input to Version or process it as a string
            return version as? Version ?: version.toString().run {
                // Check if the version is a snapshot
                val isSnapshot = endsWith(SNAPSHOT_SUFFIX, ignoreCase = true)
                // Extract the name without the snapshot suffix
                val name = if (isSnapshot) dropLast(SNAPSHOT_SUFFIX.length) else this
                // Create and return a Version instance
                Version(name = name, isSnapshot = isSnapshot)
            }
        }
    }
}
