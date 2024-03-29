package ua.railian.data.result.safe.combine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ua.railian.data.result.combine
import ua.railian.data.result.DataResult

//region flatCombineCatching
public inline fun <R, F, T1, T2, E> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2,
    ) { result1, result2 ->
        flatCombineCatching(
            result1 = result1,
            result2 = result2,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2 ->
                transformValues(v1, v2)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3,
    ) { result1, result2, result3 ->
        flatCombineCatching(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3 ->
                transformValues(v1, v2, v3)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4,
    ) { result1, result2, result3, result4 ->
        flatCombineCatching(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4 ->
                transformValues(v1, v2, v3, v4)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4, flow5,
    ) { result1, result2, result3, result4, result5 ->
        flatCombineCatching(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4, v5 ->
                transformValues(v1, v2, v3, v4, v5)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> DataResult.Factory.flatCombineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4, flow5, flow6,
    ) { result1, result2, result3, result4, result5, result6 ->
        flatCombineCatching(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            result6 = result6,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4, v5, v6 ->
                transformValues(v1, v2, v3, v4, v5, v6)
            },
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.flatCombineCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(flows) { results ->
        flatCombineCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.flatCombineCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(*flows) { results ->
        flatCombineCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Iterable<Flow<DataResult<T, E>>>.flatCombineAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(this) { results ->
        DataResult.flatCombineCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Array<Flow<DataResult<T, E>>>.flatCombineAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(*this) { results ->
        DataResult.flatCombineCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}
//endregion

//region combineCatching
public inline fun <R, F, T1, T2, E> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2,
    ) { result1, result2 ->
        combineCatching(
            result1 = result1,
            result2 = result2,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2 ->
                transformValues(v1, v2)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3,
    ) { result1, result2, result3 ->
        combineCatching(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3 ->
                transformValues(v1, v2, v3)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3, T4) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4,
    ) { result1, result2, result3, result4 ->
        combineCatching(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4 ->
                transformValues(v1, v2, v3, v4)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4, flow5,
    ) { result1, result2, result3, result4, result5 ->
        combineCatching(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4, v5 ->
                transformValues(v1, v2, v3, v4, v5)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> DataResult.Factory.combineCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5, T6) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4, flow5, flow6,
    ) { result1, result2, result3, result4, result5, result6 ->
        combineCatching(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            result6 = result6,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4, v5, v6 ->
                transformValues(v1, v2, v3, v4, v5, v6)
            },
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.combineCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combine(flows) { results ->
        combineCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.combineCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combine(*flows) { results ->
        combineCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Iterable<Flow<DataResult<T, E>>>.combineAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combine(this) { results ->
        DataResult.combineCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Array<Flow<DataResult<T, E>>>.combineAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combine(*this) { results ->
        DataResult.combineCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}
//endregion