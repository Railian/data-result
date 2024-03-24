package ua.railian.data.result

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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


/**
 * Returns a [Flow] whose values are generated with [transform] function by combining
 * the most recently emitted values by each flow.
 */
@Suppress("UNCHECKED_CAST")
public fun <T1, T2, T3, T4, T5, T6, R> combine(
    flow: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    transform: suspend (T1, T2, T3, T4, T5, T6) -> R
): Flow<R> = combine(
    flow, flow2, flow3, flow4, flow5, flow6,
) { args: Array<*> ->
    transform(
        args[0] as T1,
        args[1] as T2,
        args[2] as T3,
        args[3] as T4,
        args[4] as T5,
        args[5] as T6,
    )
}
