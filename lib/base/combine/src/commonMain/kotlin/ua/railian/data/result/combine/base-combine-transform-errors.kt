package ua.railian.data.result.combine

import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.collections.errors
import ua.railian.data.result.collections.values
import ua.railian.data.result.failure
import ua.railian.data.result.isFailure
import ua.railian.data.result.success

//region combine
public inline fun <R, F, T1, T2, E> Factory.combine(
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

public inline fun <R, F, T1, T2, T3, E> Factory.combine(
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

public inline fun <R, F, T1, T2, T3, T4, E> Factory.combine(
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

public inline fun <R, F, T1, T2, T3, T4, T5, E> Factory.combine(
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

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> Factory.combine(
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

public inline fun <R, F, T, E> Factory.combine(
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

public inline fun <R, F, T, E> Factory.combine(
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
//endregion

//region flatCombine
public inline fun <R, F, T1, T2, E> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2) -> DataResult<R, F>,
): DataResult<R, F> {
    if (result1.isFailure() || result2.isFailure()) {
        val errors = listOf(result1, result2).errors
        return transformErrors(errors)
    }
    return transformValues(result1.value, result2.value)
}

public inline fun <R, F, T1, T2, T3, E> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2, T3) -> DataResult<R, F>,
): DataResult<R, F> {
    if (result1.isFailure() || result2.isFailure() || result3.isFailure()) {
        val errors = listOf(result1, result2, result3).errors
        return transformErrors(errors)
    }
    return transformValues(result1.value, result2.value, result3.value)
}

public inline fun <R, F, T1, T2, T3, T4, E> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): DataResult<R, F> {
    if (
        result1.isFailure() || result2.isFailure() ||
        result3.isFailure() || result4.isFailure()
    ) {
        val errors = listOf(result1, result2, result3, result4).errors
        return transformErrors(errors)
    }
    return transformValues(
        result1.value, result2.value,
        result3.value, result4.value,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): DataResult<R, F> {
    if (
        result1.isFailure() || result2.isFailure() || result3.isFailure() ||
        result4.isFailure() || result5.isFailure()
    ) {
        val errors = listOf(result1, result2, result3, result4, result5).errors
        return transformErrors(errors)
    }
    return transformValues(
        result1.value, result2.value, result3.value,
        result4.value, result5.value,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): DataResult<R, F> {
    if (
        result1.isFailure() || result2.isFailure() || result3.isFailure() ||
        result4.isFailure() || result5.isFailure() || result6.isFailure()
    ) {
        val errors = listOf(result1, result2, result3, result4, result5, result6).errors
        return transformErrors(errors)
    }
    return transformValues(
        result1.value, result2.value, result3.value,
        result4.value, result5.value, result6.value,
    )
}

public inline fun <R, F, T, E> Factory.flatCombine(
    results: Iterable<DataResult<T, E>>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    val errors = results.errors
    if (errors.isNotEmpty()) {
        return transformErrors(errors)
    }
    val values = results.values
    return transformValues(values)
}

public inline fun <R, F, T, E> Factory.flatCombine(
    vararg results: DataResult<T, E>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatCombine(
        results = results.toList(),
        transformErrors = transformErrors,
        transformValues = transformValues,
    )
}
//endregion
