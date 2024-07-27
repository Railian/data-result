@file:OptIn(ExperimentalContracts::class)

package ua.railian.data.result

import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.DataResult.Success
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

//region Factory
/**
 * Builds a new [DataResult] by populating a [Factory] using the given [builder].
 */
public inline fun <R, F> Factory.build(
    builder: Factory.() -> DataResult<R, F>,
): DataResult<R, F> = builder()
//endregion

//region Transformations
/**
 * Returns the encapsulated result of the given [transform] function
 * applied to the encapsulated value if this instance represents [success][isSuccess]
 * or the original encapsulated error if it is [failure][isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 */
public inline fun <R, T, E> DataResult<T, E>.map(
    transform: (value: T) -> R,
): DataResult<R, E> = flatMap { success(transform(it)) }

/**
 * Returns the result of the given [transform] function
 * applied to the encapsulated value if this instance represents [success][isSuccess]
 * or the original result if it is [failure][isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 */
public inline fun <R, F, T, E : F> DataResult<T, E>.flatMap(
    transform: Factory.(value: T) -> DataResult<R, F>,
): DataResult<R, F> {
    contract { callsInPlace(transform, InvocationKind.AT_MOST_ONCE) }
    @Suppress("UNCHECKED_CAST")
    return when {
        isFailure() -> this as DataResult<R, F>
        else -> Factory.transform(value)
    }
}

/**
 * Returns the encapsulated result of the given [transform] function
 * applied to the encapsulated error if this instance represents [failure][isFailure]
 * or the original encapsulated value if it is [success][isSuccess].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 */
public inline fun <R, T : R, E> DataResult<T, E>.recover(
    transform: (error: E) -> R,
): Success<R, E> = flatRecover { success(transform(it)) } as Success<R, E>

/**
 * Returns the result of the given [transform] function
 * applied to the encapsulated error if this instance represents [failure][isFailure]
 * or the original result if it is [success][isSuccess].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 */
public inline fun <R, F, T : R, E> DataResult<T, E>.flatRecover(
    transform: Factory.(error: E) -> DataResult<R, F>,
): DataResult<R, F> {
    contract { callsInPlace(transform, InvocationKind.AT_MOST_ONCE) }
    @Suppress("UNCHECKED_CAST")
    return when {
        isSuccess() -> this as DataResult<R, F>
        else -> Factory.transform(error)
    }
}

/**
 * Returns the result of the given [transform] function applied to the original result.
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 */
public inline fun <R, F, T, E> DataResult<T, E>.transform(
    transform: Factory.(result: DataResult<T, E>) -> DataResult<R, F>,
): DataResult<R, F> {
    contract { callsInPlace(transform, InvocationKind.EXACTLY_ONCE) }
    return Factory.transform(this)
}
//endregion
