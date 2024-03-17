package ua.railian.gradle

data class Version(
    val name: String,
    val isSnapshot: Boolean = false,
) {

    private val fullName = buildString {
        append(name)
        if (isSnapshot) append(SNAPSHOT_SUFFIX)
    }

    override fun toString(): String = fullName

    companion object {

        private const val SNAPSHOT_SUFFIX = "-SNAPSHOT"

        fun from(version: Any): Version {
            return version as? Version ?: version.toString().run {
                val isSnapshot = endsWith(SNAPSHOT_SUFFIX, ignoreCase = true)
                val name = if (isSnapshot) dropLast(SNAPSHOT_SUFFIX.length) else this
                Version(name = name, isSnapshot = isSnapshot)
            }
        }
    }
}
