package ua.railian.data.result.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import ua.railian.data.result.DataResult
import ua.railian.data.result.flatMap
import ua.railian.data.result.flatRecover
import ua.railian.data.result.flatten
import ua.railian.data.result.fold
import ua.railian.data.result.getOrDefault
import ua.railian.data.result.getOrElse
import ua.railian.data.result.isFailure
import ua.railian.data.result.isSuccess
import ua.railian.data.result.map
import ua.railian.data.result.onFailure
import ua.railian.data.result.onSuccess
import ua.railian.data.result.recover
import ua.railian.data.result.toDataResult
import ua.railian.data.result.transform
import kotlinx.coroutines.flow.flow as kotlinFlow

/**
 * Awaits for completion of this value without blocking the thread
 * and returns the resulting value encapsulated as [success][isSuccess]
 * or returns the result received with [handleException] function if it was failed.
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [handleException] function.
 */
public suspend fun <R, F, T : R> Deferred<T>.awaitAsDataResult(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
): DataResult<R, F> {
    return runCatching { await() }
        .toDataResult(handleException)
}

public inline fun <T, E> DataResult.Factory.flow(
    crossinline block: suspend DataResultFlowCollector<T, E>.() -> Unit
): Flow<DataResult<T, E>> {
    return kotlinFlow {
        block(object : DataResultFlowCollector<T, E>, FlowCollector<DataResult<T, E>> by this {
            override suspend fun emitSuccess(value: T) = emit(value = success(value))
            override suspend fun emitFailure(error: E) = emit(value = failure(error))
        })
    }
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

public fun <T> Flow<DataResult<T, *>>.filterIsSuccess(
): Flow<DataResult.Success<T, Nothing>> {
    return filterIsInstance<DataResult.Success<T, Nothing>>()
}

public inline fun <T> Flow<DataResult<T, *>>.filterIsSuccess(
    crossinline predicate: suspend (value: T) -> Boolean,
): Flow<DataResult.Success<T, Nothing>> {
    return filterIsSuccess().filter { predicate(it.value) }
}

public fun <E> Flow<DataResult<*, E>>.filterIsFailure(
): Flow<DataResult.Failure<Nothing, E>> {
    return filterIsInstance<DataResult.Failure<Nothing, E>>()
}

public inline fun <E> Flow<DataResult<*, E>>.filterIsFailure(
    crossinline predicate: suspend (error: E) -> Boolean,
): Flow<DataResult.Failure<Nothing, E>> {
    return filterIsFailure().filter { predicate(it.error) }
}

public inline val <T> Flow<DataResult<T, *>>.valueFlow: Flow<T>
    get() = filterIsSuccess().map { it.value }

public inline val <E> Flow<DataResult<*, E>>.errorFlow: Flow<E>
    get() = filterIsFailure().map { it.error }

public inline fun <R, T : R, E> Flow<DataResult<T, E>>.valueOrElseFlow(
    crossinline default: suspend (error: E) -> R,
): Flow<R> = map { result ->
    result.getOrElse { default(it) }
}

public fun <R, T : R, E> Flow<DataResult<T, E>>.valueOrDefaultFlow(
    defaultValue: R,
): Flow<R> = map { result ->
    result.getOrDefault(defaultValue)
}

public inline fun <R, T, E> Flow<DataResult<T, E>>.valueOrDefaultFlow(
    defaultValue: R,
    crossinline transform: suspend (value: T) -> R,
): Flow<R> = map { result ->
    result.map { transform(it) }.getOrDefault(defaultValue)
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

public inline fun <R, T, E> Flow<DataResult<T, E>>.mapResult(
    crossinline transform: suspend (value: T) -> R,
): Flow<DataResult<R, E>> = map { result ->
    result.map { transform(it) }
}

public inline fun <R, F, T, E : F> Flow<DataResult<T, E>>.flatMapResult(
    crossinline transform: suspend DataResult.Factory.(value: T) -> DataResult<R, F>,
): Flow<DataResult<R, F>> = map { result ->
    result.flatMap { transform(it) }
}

public inline fun <R, T : R, E> Flow<DataResult<T, E>>.recoverResult(
    crossinline transform: suspend (error: E) -> R,
): Flow<DataResult.Success<R, E>> = map { result ->
    result.recover { transform(it) }
}

public inline fun <R, F, T : R, E> Flow<DataResult<T, E>>.flatRecoverResult(
    crossinline transform: suspend DataResult.Factory.(error: E) -> DataResult<R, F>,
): Flow<DataResult<R, F>> = map { result ->
    result.flatRecover { transform(it) }
}

public inline fun <R, F, T, E> Flow<DataResult<T, E>>.transformResult(
    crossinline transform: suspend DataResult.Factory.(DataResult<T, E>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> = map { result ->
    result.transform { transform(it) }
}

public inline fun <T, E> Flow<DataResult<T, E>>.onEachSuccess(
    crossinline action: suspend (value: T) -> Unit,
): Flow<DataResult<T, E>> = onEach { result ->
    result.onSuccess { action(it) }
}

public inline fun <T, E> Flow<DataResult<T, E>>.onEachFailure(
    crossinline action: suspend (error: E) -> Unit,
): Flow<DataResult<T, E>> = onEach { result ->
    result.onFailure { action(it) }
}

public fun <T, E1, E2, F> Flow<DataResult<DataResult<T, E1>, E2>>.flattenResult(
): Flow<DataResult<T, F>> where E1 : F, E2 : F = map { result -> result.flatten() }

public inline fun <R, F, T, E : F> DataResult<T, E>.flatMapFlow(
    transform: (value: T) -> Flow<DataResult<R, F>>,
): Flow<DataResult<R, F>> = fold(
    onFailure = { flowOf(DataResult.failure(it)) },
    onSuccess = { transform(it) }
)

@OptIn(ExperimentalCoroutinesApi::class)
public inline fun <R, F, T, E : F> Flow<DataResult<T, E>>.flatMapLatestResultFlow(
    crossinline transform: suspend (value: T) -> Flow<DataResult<R, F>>,
): Flow<DataResult<R, F>> = flatMapLatest { result ->
    result.flatMapFlow { transform(it) }
}

//public suspend fun <T, E> Flow<DataResult<T, E>>.firstSuccess(
//): DataResult.Success<T, E> {
//    return filterIsSuccess().first()
//}
//
//public suspend inline fun <T, E> Flow<DataResult<T, E>>.firstSuccess(
//    crossinline predicate: suspend (value: T) -> Boolean,
//): DataResult.Success<T, E> {
//    return filterIsSuccess().first { predicate(it.value) }
//}
//
//public suspend fun <T, E> Flow<DataResult<T, E>>.firstSuccessOrNull(
//): DataResult.Success<T, E>? {
//    return filterIsSuccess().firstOrNull()
//}
//
//public suspend inline fun <T, E> Flow<DataResult<T, E>>.firstSuccessOrNull(
//    crossinline predicate: suspend (value: T) -> Boolean,
//): DataResult.Success<T, E>? {
//    return filterIsSuccess().firstOrNull { predicate(it.value) }
//}
//
//public suspend fun <T, E> Flow<DataResult<T, E>>.firstFailure(
//): DataResult.Failure<T, E> {
//    return filterIsFailure().first()
//}
//
//public suspend inline fun <T, E> Flow<DataResult<T, E>>.firstFailure(
//    crossinline predicate: suspend (error: E) -> Boolean,
//): DataResult.Failure<T, E> {
//    return filterIsFailure().first { predicate(it.error) }
//}
//
//public suspend fun <T, E> Flow<DataResult<T, E>>.firstFailureOrNull(
//): DataResult.Failure<T, E>? {
//    return filterIsFailure().firstOrNull()
//}
//
//public suspend inline fun <T, E> Flow<DataResult<T, E>>.firstFailureOrNull(
//    crossinline predicate: suspend (error: E) -> Boolean,
//): DataResult.Failure<T, E>? {
//    return filterIsFailure().firstOrNull { predicate(it.error) }
//}
//
//public suspend fun <OUT : DataResult<R, F>, IN : OUT, R, F> Flow<IN>.first(
//    timeout: Duration,
//    handleTimeout: suspend DataResult.Factory.() -> OUT,
//): OUT {
//    return withTimeout(
//        timeout = timeout,
//        handleTimeout = { DataResult.handleTimeout() },
//        block = { first() },
//    )
//}
//
//public suspend fun <OUT : DataResult<R, F>, IN : OUT, R, F> Flow<IN>.first(
//    timeout: Duration,
//    handleTimeout: suspend DataResult.Factory.() -> OUT,
//    predicate: suspend IN.() -> Boolean,
//): OUT {
//    return withTimeout(
//        timeout = timeout,
//        handleTimeout = { DataResult.handleTimeout() },
//        block = { first(predicate) },
//    )
//}
//
//public suspend fun <OUT : DataResult<R, F>, IN : OUT, R, F> Flow<IN>.firstOrNull(
//    timeout: Duration,
//    handleTimeout: suspend DataResult.Factory.() -> OUT?,
//): OUT? {
//    return withTimeout(
//        timeout = timeout,
//        handleTimeout = { DataResult.handleTimeout() },
//        block = { firstOrNull() },
//    )
//}
//
//public suspend fun <OUT : DataResult<R, F>, IN : OUT, R, F> Flow<IN>.firstOrNull(
//    timeout: Duration,
//    handleTimeout: suspend DataResult.Factory.() -> OUT?,
//    predicate: suspend IN.() -> Boolean,
//): OUT? {
//    return withTimeout(
//        timeout = timeout,
//        handleTimeout = { DataResult.handleTimeout() },
//        block = { firstOrNull(predicate) },
//    )
//}
