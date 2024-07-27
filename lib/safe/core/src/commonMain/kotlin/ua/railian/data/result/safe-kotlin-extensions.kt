package ua.railian.data.result

internal inline fun <R> runCatching(
    retryAttempts: Int,
    block: (attempt: Int) -> R,
): Result<R> {
    var attempt = 0
    while (attempt < retryAttempts - 1) {
        val result = runCatching { block(attempt++) }
        if (result.isSuccess) return result
    }
    return runCatching { block(attempt) }
}
