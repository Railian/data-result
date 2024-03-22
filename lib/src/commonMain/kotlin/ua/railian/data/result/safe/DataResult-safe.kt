@file:OptIn(ExperimentalContracts::class)

package ua.railian.data.result.safe

import ua.railian.data.result.DataResult
import ua.railian.data.result.flatMap
import ua.railian.data.result.flatRecover
import ua.railian.data.result.isFailure
import ua.railian.data.result.isSuccess
import ua.railian.data.result.map
import ua.railian.data.result.recover
import ua.railian.data.result.toDataResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Builds a new [DataResult] by populating a [DataResult.Factory] using the given [builder],
 * catching any [Throwable] exception that was thrown from the [builder] function execution
 * and returning it as the result received with [handleException] function,
 * but rethrows any [Throwable] exception thrown by [handleException] function.
 */
public inline fun <R, F> DataResult.Factory.buildCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    builder: DataResult.Factory.() -> DataResult<R, F>,
): DataResult<R, F> {
    return runCatching { builder() }
        .getOrElse { handleException(it) }
}

/**
 * Calls the specified function [block] and returns its encapsulated result
 * if invocation was successful, catching any [Throwable] exception that was thrown
 * from the [block] function execution and returning it as the result received
 * with [handleException] function, but rethrows any [Throwable] exception
 * thrown by [handleException] function.
 */
public inline fun <R, F> DataResult.Factory.runCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    block: () -> R,
): DataResult<R, F> {
    return runCatching(block)
        .toDataResult(handleException)
}

/**
 * Calls the specified function [block] retrying [retryAttempts]
 * if it throws any [Throwable] exception and returns its encapsulated result
 * if invocation was successful, catching any [Throwable] exception that was thrown
 * from the [block] function during last execution and returning it as the result
 * received with [handleFinalException] function, but rethrows any [Throwable] exception
 * thrown by [handleFinalException] function.
 */
public fun <R, F> DataResult.Factory.runCatching(
    retryAttempts: Int,
    handleFinalException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    block: (attempt: Int) -> R,
): DataResult<R, F> {
    return ua.railian.data.result.runCatching(retryAttempts, block)
        .toDataResult(handleFinalException)
}

/**
 * Returns the encapsulated result of the given [transform] function
 * applied to the encapsulated value if this instance represents [success][isSuccess]
 * or the original encapsulated error if it is [failure][isFailure].
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and returning it as the result received with [handleException] function,
 * but rethrows any [Throwable] exception thrown by [handleException] function.
 *
 * See [map] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T, E : F> DataResult<T, E>.mapCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transform: (value: T) -> R,
): DataResult<R, F> = flatMapCatching(handleException) { success(transform(it)) }

/**
 * Returns the result of the given [transform] function
 * applied to the encapsulated value if this instance represents [success][isSuccess]
 * or the original result if it is [failure][isFailure].
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and returning it as the result received with [handleException] function,
 * but rethrows any [Throwable] exception thrown by [handleException] function.
 *
 * See [flatMap] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T, E : F> DataResult<T, E>.flatMapCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transform: DataResult.Factory.(value: T) -> DataResult<R, F>,
): DataResult<R, F> {
    contract {
        callsInPlace(handleException, InvocationKind.AT_MOST_ONCE)
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    @Suppress("UNCHECKED_CAST")
    return when {
        isFailure() -> this as DataResult<R, F>
        else -> runCatching { DataResult.transform(value) }
            .getOrElse { DataResult.handleException(it) }
    }
}

/**
 * Returns the encapsulated result of the given [transform] function
 * applied to the encapsulated error if this instance represents [failure][isFailure]
 * or the original encapsulated value if it is [success][isSuccess].
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and returning it as the result received with [handleException] function,
 * but rethrows any [Throwable] exception thrown by [handleException] function.
 *
 * See [recover] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T : R, E> DataResult<T, E>.recoverCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transform: (error: E) -> R,
): DataResult<R, F> = flatRecoverCatching(handleException) { success(transform(it)) }

/**
 * Returns the result of the given [transform] function
 * applied to the encapsulated error if this instance represents [failure][isFailure]
 * or the original result if it is [success][isSuccess].
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and returning it as the result received with [handleException] function,
 * but rethrows any [Throwable] exception thrown by [handleException] function.
 *
 * See [flatRecover] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T : R, E> DataResult<T, E>.flatRecoverCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transform: DataResult.Factory.(error: E) -> DataResult<R, F>,
): DataResult<R, F> {
    contract {
        callsInPlace(handleException, InvocationKind.AT_MOST_ONCE)
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    @Suppress("UNCHECKED_CAST")
    return when {
        isSuccess() -> this as DataResult<R, F>
        else -> runCatching { DataResult.transform(error) }
            .getOrElse { DataResult.handleException(it) }
    }
}

/**
 * Returns the result of the given [transform] function applied to the original result.
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and returning it as the result received with [handleException] function,
 * but rethrows any [Throwable] exception thrown by [handleException] function.
 *
 * See [transform] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T, E> DataResult<T, E>.transformCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transform: DataResult.Factory.(result: DataResult<T, E>) -> DataResult<R, F>,
): DataResult<R, F> {
    contract {
        callsInPlace(handleException, InvocationKind.AT_MOST_ONCE)
        callsInPlace(transform, InvocationKind.EXACTLY_ONCE)
    }
    return runCatching { DataResult.transform(this) }
        .getOrElse { DataResult.handleException(it) }
}
