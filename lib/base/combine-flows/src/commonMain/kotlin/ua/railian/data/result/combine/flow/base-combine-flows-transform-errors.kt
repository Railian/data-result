package ua.railian.data.result.combine.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine as kotlinCombine
import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.combine.combine
import ua.railian.data.result.combine.flatCombine

//region combine
public inline fun <R, F, T1, T2, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2) -> R,
): Flow<DataResult<R, F>> {
    return kotlinCombine(
        flow1, flow2,
    ) { result1, result2 ->
        combine(
            result1 = result1,
            result2 = result2,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2 ->
                transformValues(v1, v2)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3) -> R,
): Flow<DataResult<R, F>> {
    return kotlinCombine(
        flow1, flow2, flow3,
    ) { result1, result2, result3 ->
        combine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3 ->
                transformValues(v1, v2, v3)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3, T4) -> R,
): Flow<DataResult<R, F>> {
    return kotlinCombine(
        flow1, flow2, flow3, flow4,
    ) { result1, result2, result3, result4 ->
        combine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4 ->
                transformValues(v1, v2, v3, v4)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5) -> R,
): Flow<DataResult<R, F>> {
    return kotlinCombine(
        flow1, flow2, flow3, flow4, flow5,
    ) { result1, result2, result3, result4, result5 ->
        combine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4, v5 ->
                transformValues(v1, v2, v3, v4, v5)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5, T6) -> R,
): Flow<DataResult<R, F>> {
    return ua.railian.data.result.flow.combine(
        flow1, flow2, flow3, flow4, flow5, flow6,
    ) { result1, result2, result3, result4, result5, result6 ->
        combine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            result6 = result6,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4, v5, v6 ->
                transformValues(v1, v2, v3, v4, v5, v6)
            },
        )
    }
}

public inline fun <R, F, T, E> Factory.combineFlows(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return kotlinCombine(flows) { results ->
        combine(
            results = results,
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Factory.combineFlows(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return kotlinCombine(*flows) { results ->
        combine(
            results = results,
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}
//endregion

//region flatCombine
public inline fun <R, F, T1, T2, E> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return kotlinCombine(
        flow1, flow2,
    ) { result1, result2 ->
        flatCombine(
            result1 = result1,
            result2 = result2,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2 ->
                transformValues(v1, v2)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return kotlinCombine(
        flow1, flow2, flow3,
    ) { result1, result2, result3 ->
        flatCombine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3 ->
                transformValues(v1, v2, v3)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, E> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return kotlinCombine(
        flow1, flow2, flow3, flow4,
    ) { result1, result2, result3, result4 ->
        flatCombine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4 ->
                transformValues(v1, v2, v3, v4)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return kotlinCombine(
        flow1, flow2, flow3, flow4, flow5,
    ) { result1, result2, result3, result4, result5 ->
        flatCombine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4, v5 ->
                transformValues(v1, v2, v3, v4, v5)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return ua.railian.data.result.flow.combine(
        flow1, flow2, flow3, flow4, flow5, flow6
    ) { result1, result2, result3, result4, result5, result6 ->
        flatCombine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            result6 = result6,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2, v3, v4, v5, v6 ->
                transformValues(v1, v2, v3, v4, v5, v6)
            },
        )
    }
}

public inline fun <R, F, T, E> Factory.flatCombineFlows(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return kotlinCombine(flows) { results ->
        flatCombine(
            results = results,
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Factory.flatCombineFlows(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline transformErrors: suspend Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return kotlinCombine(*flows) { results ->
        flatCombine(
            results = results,
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}
//endregion
