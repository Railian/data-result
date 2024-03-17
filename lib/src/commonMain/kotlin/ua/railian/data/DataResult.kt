@file:OptIn(ExperimentalContracts::class)

package ua.railian.data

import kotlinx.coroutines.Deferred
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * A discriminated union that encapsulates a successful outcome with a value of type [T]
 * or a failure with an error of type [E].
 */
public sealed interface DataResult<out T, out E> {

    @JvmInline
    public value class Success<T> internal constructor(
        public val value: T,
    ) : DataResult<T, Nothing>

    @JvmInline
    public value class Failure<E> internal constructor(
        public val error: E,
    ) : DataResult<Nothing, E>

    /**
     * Companion object for [DataResult] class that contains its factory functions
     * [success], [failure] and [runCatching].
     */
    public companion object Factory {

        /**
         * Returns an instance that encapsulates the given [value] as successful value.
         */
        public fun <T> success(value: T): Success<T> = Success(value)

        /**
         * Returns an instance that encapsulates the given [error] as failure.
         */
        public fun <E> failure(error: E): Failure<E> = Failure(error)

        /**
         * Calls the specified function [block] and returns its encapsulated result
         * if invocation was successful, catching any [Throwable] exception that was thrown
         * from the [block] function execution and encapsulating it as a failure
         * with [onFailure] function.
         */
        public inline fun <R, F> runCatching(
            onFailure: (exception: Throwable) -> F,
            block: () -> R,
        ): DataResult<R, F> {
            return runCatching(block)
                .toDataResult(onFailure)
        }
    }
}

/**
 * Returns the result of the encapsulated value if original instance represents [success][Result.isSuccess]
 * or the the result of the encapsulated with [onFailure] function error if it is [failure][Result.isFailure].
 */
public inline fun <T, F> Result<T>.toDataResult(
    onFailure: (exception: Throwable) -> F,
): DataResult<T, F> = fold(
    onSuccess = { DataResult.success(it) },
    onFailure = { DataResult.failure(onFailure(it)) }
)


/**
 * Awaits for completion of this value without blocking the thread
 * and returns the resulting value or error encapsulated to [DataResult].
 */
public suspend fun <T, F> Deferred<T>.awaitAsDataResult(
    onFailure: (exception: Throwable) -> F,
): DataResult<T, F> = DataResult.runCatching(onFailure) { await() }

/**
 * Returns `true` if this instance represents a successful outcome.
 * In this case [isFailure] returns `false`.
 */
public fun <T, E> DataResult<T, E>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is DataResult.Success<T>)
        returns(false) implies (this@isSuccess is DataResult.Failure<E>)
    }
    return this is DataResult.Success<T>
}

/**
 * Returns `true` if this instance represents a failed outcome.
 * In this case [isSuccess] returns `false`.
 */
public fun <T, E> DataResult<T, E>.isFailure(): Boolean {
    contract {
        returns(true) implies (this@isFailure is DataResult.Failure<E>)
        returns(false) implies (this@isFailure is DataResult.Success<T>)
    }
    return this is DataResult.Failure<E>
}

/**
 * Returns the encapsulated value if this instance represents [success][isSuccess]
 * or `null` if it is [failure][isFailure].
 *
 * This function is a shorthand for `getOrElse { null }` (see [getOrElse])
 * or `fold(onSuccess = { it }, onFailure = { null })` (see [fold]).
 */
public fun <T, E> DataResult<T, E>.getOrNull(): T? = when {
    isSuccess() -> value
    else -> null
}

/**
 * Returns the encapsulated error if this instance represents [failure][isFailure]
 * or `null` if it is [success][isSuccess].
 *
 * This function is a shorthand for `fold(onSuccess = { null }, onFailure = { it })` (see [fold]).
 */
public fun <T, E> DataResult<T, E>.errorOrNull(): E? = when {
    isFailure() -> error
    else -> null
}

/**
 * Returns the encapsulated value if this instance represents [success][isSuccess]
 * or the result of [onFailure] function for the encapsulated error if it is [failure][isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [onFailure] function.
 *
 * This function is a shorthand for `fold(onSuccess = { it }, onFailure = onFailure)` (see [fold]).
 */
public inline fun <R, T : R, E> DataResult<T, E>.getOrElse(
    onFailure: (error: E) -> R,
): R = when {
    isSuccess() -> value
    else -> onFailure(error)
}

/**
 * Returns the encapsulated value if this instance represents [success][isSuccess]
 * or the [defaultValue] if it is [failure][isFailure].
 *
 * This function is a shorthand for `getOrElse { defaultValue }` (see [getOrElse]).
 */
public fun <R, T : R, E> DataResult<T, E>.getOrDefault(
    defaultValue: R,
): R = when {
    isSuccess() -> value
    else -> defaultValue
}

/**
 * Returns the result of [onSuccess] for the encapsulated value if this instance represents [success][isSuccess]
 * or the result of [onFailure] function for the encapsulated error if it is [failure][isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [onSuccess] or by [onFailure] function.
 */
public inline fun <R, T, E> DataResult<T, E>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (error: E) -> R,
): R = when {
    isSuccess() -> onSuccess(value)
    else -> onFailure(error)
}

/**
 * Returns the encapsulated result of the given [transform] function
 * applied to the encapsulated value if this instance represents [success][isSuccess]
 * or the original encapsulated error if it is [failure][isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 *
 * See [mapCatching] for an alternative that encapsulates exceptions.
 */
public inline fun <R, T, E> DataResult<T, E>.map(
    transform: (value: T) -> R,
): DataResult<R, E> = flatMap { success(transform(it)) }

/**
 * Returns the encapsulated result of the given [transform] function
 * applied to the encapsulated value if this instance represents [success][isSuccess]
 * or the original encapsulated error if it is [failure][isFailure].
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and encapsulates it as a failure with [onFailure] function.
 *
 * See [map] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T, E : F> DataResult<T, E>.mapCatching(
    onFailure: (exception: Throwable) -> F,
    transform: (value: T) -> R,
): DataResult<R, F> = flatMapCatching(onFailure) { success(transform(it)) }

/**
 * Returns the result of the given [transform] function
 * applied to the encapsulated value if this instance represents [success][isSuccess]
 * or the original result if it is [failure][isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 *
 * See [flatMapCatching] for an alternative that encapsulates exceptions.
 */
public inline fun <R, F, T, E : F> DataResult<T, E>.flatMap(
    transform: DataResult.Factory.(value: T) -> DataResult<R, F>,
): DataResult<R, F> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    @Suppress("UNCHECKED_CAST")
    return when {
        isFailure() -> this as DataResult.Failure<F>
        else -> DataResult.transform(value)
    }
}

/**
 * Returns the result of the given [transform] function
 * applied to the encapsulated value if this instance represents [success][isSuccess]
 * or the original result if it is [failure][isFailure].
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and encapsulates it as a failure with [onFailure] function.
 *
 * See [flatMap] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T, E : F> DataResult<T, E>.flatMapCatching(
    onFailure: (exception: Throwable) -> F,
    transform: DataResult.Factory.(value: T) -> DataResult<R, F>,
): DataResult<R, F> {
    contract {
        callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    @Suppress("UNCHECKED_CAST")
    return when {
        isFailure() -> this as DataResult.Failure<F>
        else -> runCatching { DataResult.transform(value) }
            .getOrElse { DataResult.failure(onFailure(it)) }
    }
}

/**
 * Returns the encapsulated result of the given [transform] function
 * applied to the encapsulated error if this instance represents [failure][isFailure]
 * or the original encapsulated value if it is [success][isSuccess].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 *
 * See [recoverCatching] for an alternative that encapsulates exceptions.
 */
public inline fun <R, T : R, E> DataResult<T, E>.recover(
    transform: (error: E) -> R,
): DataResult<R, E> = flatRecover { success(transform(it)) }

/**
 * Returns the encapsulated result of the given [transform] function
 * applied to the encapsulated error if this instance represents [failure][isFailure]
 * or the original encapsulated value if it is [success][isSuccess].
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and encapsulates it as a failure with [onFailure] function.
 *
 * See [recover] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T : R, E> DataResult<T, E>.recoverCatching(
    onFailure: (exception: Throwable) -> F,
    transform: (error: E) -> R,
): DataResult<R, F> = flatRecoverCatching(onFailure) { success(transform(it)) }

/**
 * Returns the result of the given [transform] function
 * applied to the encapsulated error if this instance represents [failure][isFailure]
 * or the original result if it is [success][isSuccess].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 *
 * See [flatRecoverCatching] for an alternative that encapsulates exceptions.
 */
public inline fun <R, F, T : R, E> DataResult<T, E>.flatRecover(
    transform: DataResult.Factory.(error: E) -> DataResult<R, F>,
): DataResult<R, F> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    @Suppress("UNCHECKED_CAST")
    return when {
        isSuccess() -> this as DataResult.Success<R>
        else -> DataResult.transform(error)
    }
}

/**
 * Returns the result of the given [transform] function
 * applied to the encapsulated error if this instance represents [failure][isFailure]
 * or the original result if it is [success][isSuccess].
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and encapsulates it as a failure with [onFailure] function.
 *
 * See [flatRecover] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T : R, E> DataResult<T, E>.flatRecoverCatching(
    onFailure: (exception: Throwable) -> F,
    transform: DataResult.Factory.(error: E) -> DataResult<R, F>,
): DataResult<R, F> {
    contract {
        callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    @Suppress("UNCHECKED_CAST")
    return when {
        isSuccess() -> this as DataResult.Success<R>
        else -> runCatching { DataResult.transform(error) }
            .getOrElse { DataResult.failure(onFailure(it)) }
    }
}

/**
 * Returns the result of the given [transform] function applied to the original result.
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 *
 * See [transformCatching] for an alternative that encapsulates exceptions.
 */
public inline fun <R, F, T, E> DataResult<T, E>.transform(
    transform: DataResult.Factory.(result: DataResult<T, E>) -> DataResult<R, F>,
): DataResult<R, F> {
    contract {
        callsInPlace(transform, InvocationKind.EXACTLY_ONCE)
    }
    return DataResult.transform(this)
}

/**
 * Returns the result of the given [transform] function applied to the original result.
 *
 * This function catches any [Throwable] exception thrown by [transform] function
 * and encapsulates it as a failure with [onFailure] function.
 *
 * See [transform] for an alternative that rethrows exceptions.
 */
public inline fun <R, F, T, E> DataResult<T, E>.transformCatching(
    onFailure: (exception: Throwable) -> F,
    transform: DataResult.Factory.(result: DataResult<T, E>) -> DataResult<R, F>,
): DataResult<R, F> {
    contract {
        callsInPlace(onFailure, InvocationKind.AT_MOST_ONCE)
        callsInPlace(transform, InvocationKind.EXACTLY_ONCE)
    }
    return runCatching { DataResult.transform(this) }
        .getOrElse { DataResult.failure(onFailure(it)) }
}

/**
 * Performs the given [action] on the encapsulated [T] value
 * if this instance represents [success][isSuccess].
 * Returns the original `DataResult` unchanged.
 */
public inline fun <T, E> DataResult<T, E>.onSuccess(
    action: (value: T) -> Unit,
): DataResult<T, E> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (isSuccess()) action(value)
    return this
}

/**
 * Performs the given [action] on the encapsulated [E] error
 * if this instance represents [failure][isFailure].
 * Returns the original `DataResult` unchanged.
 */
public inline fun <T, E> DataResult<T, E>.onFailure(
    action: (error: E) -> Unit,
): DataResult<T, E> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (isFailure()) action(error)
    return this
}
