package ua.railian.data.result.safe.combine.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ua.railian.data.result.flow.combine
import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.safe.combine.combineCatching
import ua.railian.data.result.safe.combine.flatCombineCatching

//region combineFlowsCatching
public inline fun <R, F, T1, T2, E> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
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

public inline fun <R, F, T1, T2, T3, E> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
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

public inline fun <R, F, T1, T2, T3, T4, E> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
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

public inline fun <R, F, T1, T2, T3, T4, T5, E> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
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

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> Factory.combineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
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

public inline fun <R, F, T, E> Factory.combineFlowsCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
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

public inline fun <R, F, T, E> Factory.combineFlowsCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
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
//endregion

//region flatCombineFlowsCatching
public inline fun <R, F, T1, T2, E> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2) -> DataResult<R, F>,
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

public inline fun <R, F, T1, T2, T3, E> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3) -> DataResult<R, F>,
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

public inline fun <R, F, T1, T2, T3, T4, E> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
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

public inline fun <R, F, T1, T2, T3, T4, T5, E> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
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

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> Factory.flatCombineFlowsCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
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

public inline fun <R, F, T, E> Factory.flatCombineFlowsCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(List<T>) -> DataResult<R, F>,
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

public inline fun <R, F, T, E> Factory.flatCombineFlowsCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(List<T>) -> DataResult<R, F>,
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
//endregion
