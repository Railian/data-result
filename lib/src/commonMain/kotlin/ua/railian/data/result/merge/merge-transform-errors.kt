package ua.railian.data.result.merge

import ua.railian.data.result.DataResult
import ua.railian.data.result.buildDataResultCatching
import ua.railian.data.result.errors
import ua.railian.data.result.filterIsFailure
import ua.railian.data.result.filterIsSuccess
import ua.railian.data.result.isFailure
import ua.railian.data.result.values

//region flatMerge
public inline fun <R, F, T1, T2, E> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): DataResult<R, F> {
    if (result1.isFailure() || result2.isFailure()) {
        val errors = listOf(result1, result2).filterIsFailure().errors
        return transformErrors(errors)
    }
    return transformValues(result1.value, result2.value)
}

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3) -> DataResult<R, F>,
): DataResult<R, F> {
    if (result1.isFailure() || result2.isFailure() || result3.isFailure()) {
        val errors = listOf(result1, result2, result3).filterIsFailure().errors
        return transformErrors(errors)
    }
    return transformValues(result1.value, result2.value, result3.value)
}

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): DataResult<R, F> {
    if (
        result1.isFailure() || result2.isFailure() ||
        result3.isFailure() || result4.isFailure()
    ) {
        val errors = listOf(result1, result2, result3, result4)
            .filterIsFailure().errors
        return transformErrors(errors)
    }
    return transformValues(
        result1.value, result2.value,
        result3.value, result4.value,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): DataResult<R, F> {
    if (
        result1.isFailure() || result2.isFailure() || result3.isFailure() ||
        result4.isFailure() || result5.isFailure()
    ) {
        val errors = listOf(result1, result2, result3, result4, result5)
            .filterIsFailure().errors
        return transformErrors(errors)
    }
    return transformValues(
        result1.value, result2.value, result3.value,
        result4.value, result5.value,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): DataResult<R, F> {
    if (
        result1.isFailure() || result2.isFailure() || result3.isFailure() ||
        result4.isFailure() || result5.isFailure() || result6.isFailure()
    ) {
        val errors = listOf(result1, result2, result3, result4, result5, result6)
            .filterIsFailure().errors
        return transformErrors(errors)
    }
    return transformValues(
        result1.value, result2.value, result3.value,
        result4.value, result5.value, result6.value,
    )
}

public inline fun <R, F, T, E> DataResult.Factory.flatMerge(
    results: Iterable<DataResult<T, E>>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    val errors = results.filterIsFailure().errors
    if (errors.isNotEmpty()) {
        return transformErrors(errors)
    }
    val values = results.filterIsSuccess().values
    return transformValues(values)
}

public inline fun <R, F, T, E> DataResult.Factory.flatMerge(
    vararg results: DataResult<T, E>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMerge(
        results = results.toList(),
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Iterable<DataResult<T, E>>.flatMergeAll(
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatMerge(
        results = this,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Array<DataResult<T, E>>.flatMergeAll(
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatMerge(
        results = this,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion

//region flatMergeCatching
public inline fun <R, F, T1, T2, E> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        flatMerge(
            result1 = result1,
            result2 = result2,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        flatMerge(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        flatMerge(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        flatMerge(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        flatMerge(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            result6 = result6,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.flatMergeCatching(
    results: Iterable<DataResult<T, E>>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        flatMerge(
            results = results,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.flatMergeCatching(
    vararg results: DataResult<T, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        flatMerge(
            results = results,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> Iterable<DataResult<T, E>>.flatMergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatMergeCatching(
        results = this,
        handleException = handleException,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Array<DataResult<T, E>>.flatMergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatMergeCatching(
        results = this,
        handleException = handleException,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion

//region merge
public inline fun <R, F, T1, T2, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2) -> R,
): DataResult<R, F> {
    return flatMerge(
        result1 = result1,
        result2 = result2,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { v1, v2 ->
            success(transformValues(v1, v2))
        },
    )
}

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3) -> R,
): DataResult<R, F> {
    return flatMerge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { v1, v2, v3 ->
            success(transformValues(v1, v2, v3))
        },
    )
}

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4) -> R,
): DataResult<R, F> {
    return flatMerge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { v1, v2, v3, v4 ->
            success(transformValues(v1, v2, v3, v4))
        },
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4, T5) -> R,
): DataResult<R, F> {
    return flatMerge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { v1, v2, v3, v4, v5 ->
            success(transformValues(v1, v2, v3, v4, v5))
        },
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4, T5, T6) -> R,
): DataResult<R, F> {
    return flatMerge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        result6 = result6,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { v1, v2, v3, v4, v5, v6 ->
            success(transformValues(v1, v2, v3, v4, v5, v6))
        },
    )
}

public inline fun <R, F, T, E> DataResult.Factory.merge(
    results: Iterable<DataResult<T, E>>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return flatMerge(
        results = results,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { success(transformValues(it)) },
    )
}

public inline fun <R, F, T, E> DataResult.Factory.merge(
    vararg results: DataResult<T, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return flatMerge(
        results = results,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { success(transformValues(it)) },
    )
}

public inline fun <R, F, T, E> Iterable<DataResult<T, E>>.mergeAll(
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.merge(
        results = this,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Array<DataResult<T, E>>.mergeAll(
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.merge(
        results = this,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion

//region mergeCatching
public inline fun <R, F, T1, T2, E> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2) -> R,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        merge(
            result1 = result1,
            result2 = result2,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3) -> R,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        merge(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4) -> R,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        merge(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4, T5) -> R,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        merge(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4, T5, T6) -> R,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        merge(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            result5 = result5,
            result6 = result6,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.mergeCatching(
    results: Iterable<DataResult<T, E>>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        merge(
            results = results,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> DataResult.Factory.mergeCatching(
    vararg results: DataResult<T, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return buildDataResultCatching(handleException) {
        merge(
            results = results,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> Iterable<DataResult<T, E>>.mergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.mergeCatching(
        results = this,
        handleException = handleException,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Array<DataResult<T, E>>.mergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.mergeCatching(
        results = this,
        handleException = handleException,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion
