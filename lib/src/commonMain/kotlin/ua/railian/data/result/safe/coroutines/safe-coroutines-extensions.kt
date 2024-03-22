package ua.railian.data.result.safe.coroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.railian.data.result.DataResult
import ua.railian.data.result.safe.flatMapCatching
import ua.railian.data.result.safe.flatRecoverCatching
import ua.railian.data.result.safe.mapCatching
import ua.railian.data.result.safe.recoverCatching
import ua.railian.data.result.safe.transformCatching

public inline fun <R, F, T, E : F> Flow<DataResult<T, E>>.mapResultCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transform: suspend (value: T) -> R,
): Flow<DataResult<R, F>> = map { result ->
    result.mapCatching(
        handleException = { handleException(it) },
        transform = { transform(it) },
    )
}

public inline fun <R, F, T, E : F> Flow<DataResult<T, E>>.flatMapResultCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transform: suspend DataResult.Factory.(value: T) -> DataResult<R, F>,
): Flow<DataResult<R, F>> = map { result ->
    result.flatMapCatching(
        handleException = { handleException(it) },
        transform = { transform(it) },
    )
}

public inline fun <R, F, T : R, E> Flow<DataResult<T, E>>.recoverResultCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transform: suspend (error: E) -> R,
): Flow<DataResult<R, F>> = map { result ->
    result.recoverCatching(
        handleException = { handleException(it) },
        transform = { transform(it) },
    )
}

public inline fun <R, F, T : R, E> Flow<DataResult<T, E>>.flatRecoverResultCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transform: suspend DataResult.Factory.(error: E) -> DataResult<R, F>,
): Flow<DataResult<R, F>> = map { result ->
    result.flatRecoverCatching(
        handleException = { handleException(it) },
        transform = { transform(it) },
    )
}

public inline fun <R, F, T, E> Flow<DataResult<T, E>>.transformResultCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transform: suspend DataResult.Factory.(DataResult<T, E>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> = map { result ->
    result.transformCatching(
        handleException = { handleException(it) },
        transform = { transform(it) },
    )
}
