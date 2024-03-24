package ua.railian.data.result.safe.combine

import kotlinx.coroutines.flow.Flow
import ua.railian.data.result.DataResult

//region flatCombineCatching
public inline fun <R, F, T1, T2, E : F> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineCatching(
        flow1 = flow1,
        flow2 = flow2,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineCatching(
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

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E : F> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineCatching(
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

public inline fun <R, F, T, E : F> DataResult.Factory.flatCombineCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.flatCombineCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Iterable<Flow<DataResult<T, E>>>.flatCombineAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return DataResult.flatCombineCatching(
        flows = this,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Array<Flow<DataResult<T, E>>>.flatCombineAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return DataResult.flatCombineCatching(
        flows = this,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}
//endregion


//region combineCatching
public inline fun <R, F, T1, T2, E : F> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2) -> R,
): Flow<DataResult<R, F>> {
    return combineCatching(
        flow1 = flow1,
        flow2 = flow2,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3) -> R,
): Flow<DataResult<R, F>> {
    return combineCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3, T4) -> R,
): Flow<DataResult<R, F>> {
    return combineCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5) -> R,
): Flow<DataResult<R, F>> {
    return combineCatching(
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

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E : F> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5, T6) -> R,
): Flow<DataResult<R, F>> {
    return combineCatching(
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

public inline fun <R, F, T, E : F> DataResult.Factory.combineCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combineCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.combineCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combineCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Iterable<Flow<DataResult<T, E>>>.combineAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return DataResult.combineCatching(
        flows = this,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Array<Flow<DataResult<T, E>>>.combineAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return DataResult.combineCatching(
        flows = this,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}
//endregion
