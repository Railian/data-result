package ua.railian.data.result.safe.combine

import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.combine.flatCombine
import ua.railian.data.result.combine.combine
import ua.railian.data.result.safe.buildCatching

//region combineCatching
public inline fun <R, F, T1, T2, E> Factory.combineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2) -> R,
): DataResult<R, F> {
    return buildCatching(handleException) {
        combine(
            result1 = result1,
            result2 = result2,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> Factory.combineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3) -> R,
): DataResult<R, F> {
    return buildCatching(handleException) {
        combine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, E> Factory.combineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4) -> R,
): DataResult<R, F> {
    return buildCatching(handleException) {
        combine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> Factory.combineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4, T5) -> R,
): DataResult<R, F> {
    return buildCatching(handleException) {
        combine(
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

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> Factory.combineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (T1, T2, T3, T4, T5, T6) -> R,
): DataResult<R, F> {
    return buildCatching(handleException) {
        combine(
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

public inline fun <R, F, T, E> Factory.combineCatching(
    results: Iterable<DataResult<T, E>>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return buildCatching(handleException) {
        combine(
            results = results,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> Factory.combineCatching(
    vararg results: DataResult<T, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return buildCatching(handleException) {
        combine(
            results = results,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}
//endregion

//region flatCombineCatching
public inline fun <R, F, T1, T2, E> Factory.flatCombineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildCatching(handleException) {
        flatCombine(
            result1 = result1,
            result2 = result2,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, E> Factory.flatCombineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2, T3) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildCatching(handleException) {
        flatCombine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, E> Factory.flatCombineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildCatching(handleException) {
        flatCombine(
            result1 = result1,
            result2 = result2,
            result3 = result3,
            result4 = result4,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T1, T2, T3, T4, T5, E> Factory.flatCombineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildCatching(handleException) {
        flatCombine(
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

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E> Factory.flatCombineCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildCatching(handleException) {
        flatCombine(
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

public inline fun <R, F, T, E> Factory.flatCombineCatching(
    results: Iterable<DataResult<T, E>>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildCatching(handleException) {
        flatCombine(
            results = results,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> Factory.flatCombineCatching(
    vararg results: DataResult<T, E>,
    handleException: Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: Factory.(List<E>) -> DataResult<R, F>,
    transformValues: Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return buildCatching(handleException) {
        flatCombine(
            results = results,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}
//endregion
