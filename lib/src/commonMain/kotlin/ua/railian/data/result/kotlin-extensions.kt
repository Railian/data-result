package ua.railian.data.result

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import kotlin.time.Duration

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

internal suspend fun <T> withTimeout(
    timeout: Duration,
    handleTimeout: suspend () -> T,
    block: suspend CoroutineScope.() -> T,
): T {
    return try {
        withTimeout(timeout, block)
    } catch (exception: TimeoutCancellationException) {
        handleTimeout()
    }
}