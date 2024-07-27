package ua.railian.data.result.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.DataResult.Success
import ua.railian.data.result.failure
import ua.railian.data.result.flatMap
import ua.railian.data.result.flatRecover
import ua.railian.data.result.fold
import ua.railian.data.result.getOrDefault
import ua.railian.data.result.map
import ua.railian.data.result.recover
import ua.railian.data.result.transform

//region Factory
public inline fun <T, E> Factory.buildFlow(
    crossinline block: suspend DataResultFlowCollector<T, E>.() -> Unit
): Flow<DataResult<T, E>> {
    return flow {
        val collector = object : DataResultFlowCollector<T, E>,
            FlowCollector<DataResult<T, E>> by this {}
        block(collector)
    }
}
//endregion

//region Extractions
public inline fun <R, T, E> Flow<DataResult<T, E>>.valuesOrDefaultFlow(
    defaultValue: R,
    crossinline transform: suspend (value: T) -> R,
): Flow<R> = map { result ->
    result.map { transform(it) }.getOrDefault(defaultValue)
}
//endregion

//region Transformations
public inline fun <R, T, E> Flow<DataResult<T, E>>.mapResult(
    crossinline transform: suspend (value: T) -> R,
): Flow<DataResult<R, E>> = map { result ->
    result.map { transform(it) }
}

public inline fun <R, F, T, E : F> Flow<DataResult<T, E>>.flatMapResult(
    crossinline transform: suspend Factory.(value: T) -> DataResult<R, F>,
): Flow<DataResult<R, F>> = map { result ->
    result.flatMap { transform(it) }
}

public inline fun <R, T : R, E> Flow<DataResult<T, E>>.recoverResult(
    crossinline transform: suspend (error: E) -> R,
): Flow<Success<R, E>> = map { result ->
    result.recover { transform(it) }
}

public inline fun <R, F, T : R, E> Flow<DataResult<T, E>>.flatRecoverResult(
    crossinline transform: suspend Factory.(error: E) -> DataResult<R, F>,
): Flow<DataResult<R, F>> = map { result ->
    result.flatRecover { transform(it) }
}

public inline fun <R, F, T, E> Flow<DataResult<T, E>>.transformResult(
    crossinline transform: suspend Factory.(result: DataResult<T, E>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> = map { result ->
    result.transform { transform(it) }
}

public inline fun <R, F, T, E : F> DataResult<T, E>.flatMapFlow(
    transform: Factory.(value: T) -> Flow<DataResult<R, F>>,
): Flow<DataResult<R, F>> = fold(
    onFailure = { flowOf(Factory.failure(it)) },
    onSuccess = { Factory.transform(it) }
)

@OptIn(ExperimentalCoroutinesApi::class)
public inline fun <R, F, T, E : F> Flow<DataResult<T, E>>.flatMapLatestResultFlow(
    crossinline transform: suspend Factory.(value: T) -> Flow<DataResult<R, F>>,
): Flow<DataResult<R, F>> = flatMapLatest { result ->
    result.flatMapFlow { transform(it) }
}
//endregion
