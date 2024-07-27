package ua.railian.data.result.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.DataResult.Failure
import ua.railian.data.result.DataResult.Success
import ua.railian.data.result.flatten
import ua.railian.data.result.fold
import ua.railian.data.result.getOrDefault
import ua.railian.data.result.getOrElse
import ua.railian.data.result.isFailure
import ua.railian.data.result.isSuccess
import ua.railian.data.result.onFailure
import ua.railian.data.result.onSuccess
import ua.railian.data.result.toDataResult

//region Factory
/**
 * Awaits for completion of this value without blocking the thread
 * and returns the resulting value encapsulated as [success][isSuccess]
 * or returns the result received with [handleException] function if it was failed.
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [handleException] function.
 */
public suspend fun <R, F, T : R> Deferred<T>.awaitAsDataResult(
    handleException: Factory.(Throwable) -> DataResult<R, F>,
): DataResult<R, F> {
    return runCatching { await() }
        .toDataResult(handleException)
}
//endregion

//region Filters
public fun <T> Flow<DataResult<T, *>>.filterIsSuccess(
): Flow<Success<T, Nothing>> {
    return filterIsInstance<Success<T, Nothing>>()
}

public inline fun <T> Flow<DataResult<T, *>>.filterIsSuccess(
    crossinline predicate: suspend (value: T) -> Boolean,
): Flow<Success<T, Nothing>> {
    return filterIsSuccess().filter { predicate(it.value) }
}

public fun <E> Flow<DataResult<*, E>>.filterIsFailure(
): Flow<Failure<Nothing, E>> {
    return filterIsInstance<Failure<Nothing, E>>()
}

public inline fun <E> Flow<DataResult<*, E>>.filterIsFailure(
    crossinline predicate: suspend (error: E) -> Boolean,
): Flow<Failure<Nothing, E>> {
    return filterIsFailure().filter { predicate(it.error) }
}

public inline fun <T, E> Flow<DataResult<T, E>>.skipSuccessIf(
    crossinline predicate: suspend (value: T) -> Boolean,
): Flow<DataResult<T, E>> {
    return filter { it.isFailure() || !predicate(it.value) }
}

public inline fun <T, E> Flow<DataResult<T, E>>.skipFailureIf(
    crossinline predicate: suspend (error: E) -> Boolean,
): Flow<DataResult<T, E>> {
    return filter { it.isSuccess() || !predicate(it.error) }
}
//endregion

//region Extractions
public inline val <T> Flow<DataResult<T, *>>.valuesFlow: Flow<T>
    get() = filterIsSuccess().map { it.value }

public inline val <E> Flow<DataResult<*, E>>.errorsFlow: Flow<E>
    get() = filterIsFailure().map { it.error }


public fun <R, T : R, E> Flow<DataResult<T, E>>.valuesOrDefaultFlow(
    defaultValue: R,
): Flow<R> = map { result ->
    result.getOrDefault(defaultValue)
}

public inline fun <R, T : R, E> Flow<DataResult<T, E>>.valuesOrElseFlow(
    crossinline default: suspend (error: E) -> R,
): Flow<R> = map { result ->
    result.getOrElse { default(it) }
}

public inline fun <R, T, E> Flow<DataResult<T, E>>.foldResult(
    crossinline onSuccess: suspend (value: T) -> R,
    crossinline onFailure: suspend (error: E) -> R,
): Flow<R> = map { result ->
    result.fold(
        onSuccess = { onSuccess(it) },
        onFailure = { onFailure(it) },
    )
}
//endregion

//region Transformations
public fun <T, E1, E2, F> Flow<DataResult<DataResult<T, E1>, E2>>.flattenResult(
    transformErrors: (List<F>) -> F,
): Flow<DataResult<T, F>> where E1 : F, E2 : F = map { result -> result.flatten(transformErrors) }

public fun <T, E1, E2, F> Flow<DataResult<DataResult<T, E1>, E2>>.flattenResult(
): Flow<DataResult<T, F>> where E1 : F, E2 : F = map { result -> result.flatten() }
//endregion

//region Side effects
public inline fun <T, E> Flow<DataResult<T, E>>.onEachSuccess(
    crossinline action: suspend Success<T, E>.(value: T) -> Unit,
): Flow<DataResult<T, E>> = onEach { result ->
    result.onSuccess { action(it) }
}

public inline fun <T, E> Flow<DataResult<T, E>>.onEachFailure(
    crossinline action: suspend Failure<T, E>.(error: E) -> Unit,
): Flow<DataResult<T, E>> = onEach { result ->
    result.onFailure { action(it) }
}
//endregion
