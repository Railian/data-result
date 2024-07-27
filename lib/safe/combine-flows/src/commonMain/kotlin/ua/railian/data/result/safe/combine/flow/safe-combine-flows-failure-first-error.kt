package ua.railian.data.result.safe.combine.flow

import kotlinx.coroutines.flow.Flow
import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.failure

//region combineFlowsCatching
public inline fun <R, F, T1, T2, E : F> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2) -> R,
): Flow<DataResult<R, F>> {
    return combineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3) -> R,
): Flow<DataResult<R, F>> {
    return combineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3, T4) -> R,
): Flow<DataResult<R, F>> {
    return combineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5) -> R,
): Flow<DataResult<R, F>> {
    return combineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E : F> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5, T6) -> R,
): Flow<DataResult<R, F>> {
    return combineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        flow6 = flow6,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Factory.combineFlowsCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combineFlowsCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Factory.combineFlowsCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combineFlowsCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}
//endregion

//region flatCombineFlowsCatching
public inline fun <R, F, T1, T2, E : F> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E : F> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlowsCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        flow6 = flow6,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Factory.flatCombineFlowsCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlowsCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Factory.flatCombineFlowsCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlowsCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}
//endregion
