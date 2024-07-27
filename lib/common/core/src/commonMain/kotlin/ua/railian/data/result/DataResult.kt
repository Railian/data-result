@file:OptIn(ExperimentalContracts::class)

package ua.railian.data.result

import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.DataResult.Failure
import ua.railian.data.result.DataResult.Success
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * A discriminated union that encapsulates a successful outcome with a value of type [T]
 * or a failure with an error of type [E].
 */
public sealed interface DataResult<out T, out E> {

     public data class Success<out T, out E> internal constructor(
        public val value: T,
    ) : DataResult<T, E>

    public data class Failure<out T, out E> internal constructor(
        public val error: E,
    ) : DataResult<T, E>

    /**
     * Companion object for [DataResult] class that contains its factory functions
     * [success], [failure], [build], etc.
     */
    public companion object Factory
}

//region Factory
/**
 * Returns an instance that encapsulates the given [value] as successful value.
 */
public fun <T> Factory.success(value: T): Success<T, Nothing> = Success(value)

/**
 * Returns an instance that encapsulates the given [error] as failure.
 */
public fun <E> Factory.failure(error: E): Failure<Nothing, E> = Failure(error)

/**
 * Returns an instance that encapsulates the given [value] as successful value
 * and define the type [E] of available failed error.
 */
public fun <T, E> Factory.typedSuccess(value: T): Success<T, E> = Success(value)

/**
 * Returns an instance that encapsulates the given [error] as failure
 * and define the type [T] of available successful value.
 */
public fun <T, E> Factory.typedFailure(error: E): Failure<T, E> = Failure(error)

/**
 * Returns the result of the encapsulated value if original instance represents [success][Result.isSuccess]
 * or returns the result received with [handleException] function if it is [failure][Result.isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [handleException] function.
 */
public inline fun <R, F, T : R> Result<T>.toDataResult(
    handleException: Factory.(Throwable) -> DataResult<R, F>,
): DataResult<R, F> {
    return fold(
        onSuccess = { Factory.success(it) },
        onFailure = { Factory.handleException(it) },
    )
}
//endregion

//region Checks
/**
 * Returns `true` if this instance represents a successful outcome.
 * In this case [isFailure] returns `false`.
 */
public fun <T, E> DataResult<T, E>.isSuccess(): Boolean {
    contract {
        returns(true) implies (this@isSuccess is Success<T, E>)
        returns(false) implies (this@isSuccess is Failure<T, E>)
    }
    return this is Success<T, E>
}

/**
 * Returns `true` if this instance represents a failed outcome.
 * In this case [isSuccess] returns `false`.
 */
public fun <T, E> DataResult<T, E>.isFailure(): Boolean {
    contract {
        returns(true) implies (this@isFailure is Failure<T, E>)
        returns(false) implies (this@isFailure is Success<T, E>)
    }
    return this is Failure<T, E>
}
//endregion

//region Extractions
/**
 * Returns the encapsulated error if this instance represents [failure][isFailure]
 * or `null` if it is [success][isSuccess].
 *
 * This function is a shorthand for `fold(onSuccess = { null }, onFailure = { it })` (see [fold]).
 */
public fun <T, E> DataResult<T, E>.errorOrNull(): E? = when (this) {
    is Success -> null
    is Failure -> error
}

/**
 * Returns the encapsulated value if this instance represents [success][isSuccess]
 * or `null` if it is [failure][isFailure].
 *
 * This function is a shorthand for `getOrElse { null }` (see [getOrElse])
 * or `fold(onSuccess = { it }, onFailure = { null })` (see [fold]).
 */
public fun <T, E> DataResult<T, E>.getOrNull(): T? = when (this) {
    is Success -> value
    is Failure -> null
}

/**
 * Returns the encapsulated value if this instance represents [success][isSuccess]
 * or the [defaultValue] if it is [failure][isFailure].
 *
 * This function is a shorthand for `getOrElse { defaultValue }` (see [getOrElse]).
 */
public fun <R, T : R, E> DataResult<T, E>.getOrDefault(
    defaultValue: R,
): R = when (this) {
    is Success -> value
    is Failure -> defaultValue
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
): R = when (this) {
    is Success -> value
    is Failure -> onFailure(error)
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
): R = when (this) {
    is Success -> onSuccess(value)
    is Failure -> onFailure(error)
}
//endregion

//region Transformations
/**
 * Returns encapsulated value of inner result if both results were successful,
 * or one of encapsulated errors as a failure from these results.
 */
public fun <T, E1, E2, F> DataResult<DataResult<T, E1>, E2>.flatten(
    transformErrors: (List<F>) -> F,
): DataResult<T, F> where E1 : F, E2 : F {
    if (this.isSuccess() && value.isSuccess()) return value
    val innerError = getOrNull()?.errorOrNull()
    val outerError = this.errorOrNull()
    val errors: List<F> = listOfNotNull(innerError, outerError)
    return Factory.failure(transformErrors(errors))
}

/**
 * Returns encapsulated value of inner result if both results were successful,
 * or one of encapsulated errors as a failure from these results.
 */
public fun <T, E1, E2, F> DataResult<DataResult<T, E1>, E2>.flatten(
): DataResult<T, F> where E1 : F, E2 : F = flatten(List<F>::first)
//endregion

//region Side effects
/**
 * Performs the given [action] on the encapsulated [T] value
 * if this instance represents [success][isSuccess].
 * Returns the original `DataResult` unchanged.
 */
public inline fun <T, E> DataResult<T, E>.onSuccess(
    action: Success<T, E>.(value: T) -> Unit,
): DataResult<T, E> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }
    if (isSuccess()) action(value)
    return this
}

/**
 * Performs the given [action] on the encapsulated [E] error
 * if this instance represents [failure][isFailure].
 * Returns the original `DataResult` unchanged.
 */
public inline fun <T, E> DataResult<T, E>.onFailure(
    action: Failure<T, E>.(error: E) -> Unit,
): DataResult<T, E> {
    contract { callsInPlace(action, InvocationKind.AT_MOST_ONCE) }
    if (isFailure()) action(error)
    return this
}
//endregion
