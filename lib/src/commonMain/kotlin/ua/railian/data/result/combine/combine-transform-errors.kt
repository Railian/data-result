package ua.railian.data.result.combine

import ua.railian.data.result.DataResult
import ua.railian.data.result.errors
import ua.railian.data.result.filterIsFailure
import ua.railian.data.result.filterIsSuccess
import ua.railian.data.result.isFailure
import ua.railian.data.result.values

//region flatCombine
public inline fun <R, F, T1, T2, E> DataResult.Factory.flatCombine(
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

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.flatCombine(
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

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.flatCombine(
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

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.flatCombine(
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

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> DataResult.Factory.flatCombine(
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

public inline fun <R, F, T, E> DataResult.Factory.flatCombine(
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

public inline fun <R, F, T, E> DataResult.Factory.flatCombine(
    vararg results: DataResult<T, E>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatCombine(
        results = results.toList(),
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Iterable<DataResult<T, E>>.flatCombineAll(
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatCombine(
        results = this@flatCombineAll,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E> Array<DataResult<T, E>>.flatCombineAll(
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatCombine(
        results = this@flatCombineAll,
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion

//region combine
public inline fun <R, F, T1, T2, E> DataResult.Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2) -> R,
): DataResult<R, F> {
    return flatCombine(
        result1 = result1,
        result2 = result2,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { v1, v2 ->
            success(transformValues(v1, v2))
        },
    )
}

public inline fun <R, F, T1, T2, T3, E> DataResult.Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3) -> R,
): DataResult<R, F> {
    return flatCombine(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { v1, v2, v3 ->
            success(transformValues(v1, v2, v3))
        },
    )
}

public inline fun <R, F, T1, T2, T3, T4, E> DataResult.Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4) -> R,
): DataResult<R, F> {
    return flatCombine(
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

public inline fun <R, F, T1, T2, T3, T4, T5, E> DataResult.Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4, T5) -> R,
): DataResult<R, F> {
    return flatCombine(
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

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> DataResult.Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4, T5, T6) -> R,
): DataResult<R, F> {
    return flatCombine(
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

public inline fun <R, F, T, E> DataResult.Factory.combine(
    results: Iterable<DataResult<T, E>>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return flatCombine(
        results = results,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { success(transformValues(it)) },
    )
}

public inline fun <R, F, T, E> DataResult.Factory.combine(
    vararg results: DataResult<T, E>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return flatCombine(
        results = results,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { success(transformValues(it)) },
    )
}

public inline fun <R, F, T, E> Iterable<DataResult<T, E>>.combineAll(
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.flatCombine(
        results = this@combineAll,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { success(transformValues(it)) },
    )
}

public inline fun <R, F, T, E> Array<DataResult<T, E>>.combineAll(
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.flatCombine(
        results = this@combineAll,
        transformErrors = { failure(transformErrors(it)) },
        transformValues = { success(transformValues(it)) },
    )
}
//endregion
