package ua.railian.data.result.merge

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ua.railian.data.result.DataResult

//region flatMerge
public inline fun <R, F, T1, T2, E> DataResult.Factory.flatMerge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2,
    ) { result1, result2 ->
        flatMerge(
            result1 = result1,
            result2 = result2,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2 ->
                transformValues(v1, v2)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.flatMerge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3,
    ) { result1, result2, result3 ->
        flatMerge(
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

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.flatMerge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4,
    ) { result1, result2, result3, result4 ->
        flatMerge(
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

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.flatMerge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4, flow5,
    ) { result1, result2, result3, result4, result5 ->
        flatMerge(
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

public inline fun <R, F, T, E> DataResult.Factory.flatMerge(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(flows) { results ->
        flatMerge(
            results = results,
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.flatMerge(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(*flows) { results ->
        flatMerge(
            results = results,
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Iterable<Flow<DataResult<T, E>>>.flatMergeAll(
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return DataResult.flatMerge(
        flows = this,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Array<Flow<DataResult<T, E>>>.flatMergeAll(
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return DataResult.flatMerge(
        flows = this,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion

//region flatMergeCatching
public inline fun <R, F, T1, T2, E> DataResult.Factory.flatMergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2,
    ) { result1, result2 ->
        flatMergeCatching(
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

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.flatMergeCatching(
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
        flatMergeCatching(
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

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.flatMergeCatching(
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
        flatMergeCatching(
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

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.flatMergeCatching(
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
        flatMergeCatching(
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

public inline fun <R, F, T, E> DataResult.Factory.flatMergeCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(flows) { results ->
        flatMergeCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.flatMergeCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return combine(*flows) { results ->
        flatMergeCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Iterable<Flow<DataResult<T, E>>>.flatMergeAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return DataResult.flatMergeCatching(
        flows = this,
        handleException = handleException,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Array<Flow<DataResult<T, E>>>.flatMergeAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend DataResult.Factory.(List<E>) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return DataResult.flatMergeCatching(
        flows = this,
        handleException = handleException,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion

//region merge
public inline fun <R, F, T1, T2, E> DataResult.Factory.merge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2,
    ) { result1, result2 ->
        merge(
            result1 = result1,
            result2 = result2,
            transformErrors = { transformErrors(it) },
            transformValues = { v1, v2 ->
                transformValues(v1, v2)
            },
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.merge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3,
    ) { result1, result2, result3 ->
        merge(
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

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.merge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3, T4) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4,
    ) { result1, result2, result3, result4 ->
        merge(
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

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.merge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4, flow5,
    ) { result1, result2, result3, result4, result5 ->
        merge(
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

public inline fun <R, F, T, E> DataResult.Factory.merge(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combine(flows) { results ->
        merge(
            results = results,
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.merge(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combine(*flows) { results ->
        merge(
            results = results,
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Iterable<Flow<DataResult<T, E>>>.mergeAll(
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return DataResult.merge(
        flows = this,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Array<Flow<DataResult<T, E>>>.mergeAll(
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return DataResult.merge(
        flows = this,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion

//region mergeCatching
public inline fun <R, F, T1, T2, E> DataResult.Factory.mergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2,
    ) { result1, result2 ->
        mergeCatching(
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

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.mergeCatching(
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
        mergeCatching(
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

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.mergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline  handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (T1, T2, T3, T4) -> R,
): Flow<DataResult<R, F>> {
    return combine(
        flow1, flow2, flow3, flow4,
    ) { result1, result2, result3, result4 ->
        mergeCatching(
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

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.mergeCatching(
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
        mergeCatching(
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

public inline fun <R, F, T, E> DataResult.Factory.mergeCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combine(flows) { results ->
        mergeCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.mergeCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return combine(*flows) { results ->
        mergeCatching(
            results = results,
            handleException = { handleException(it) },
            transformErrors = { transformErrors(it) },
            transformValues = { transformValues(it) },
        )
    }
}

public inline fun <R, F, T, E> Iterable<Flow<DataResult<T, E>>>.mergeAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return DataResult.mergeCatching(
        flows = this,
        handleException = handleException,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Array<Flow<DataResult<T, E>>>.mergeAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformErrors: suspend (List<E>) -> F,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return DataResult.mergeCatching(
        flows = this,
        handleException = handleException,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion