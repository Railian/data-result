package ua.railian.data.result.combine

import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.failure

//region combine
public inline fun <R, T1, T2, E> Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    transformValues: (T1, T2) -> R,
): DataResult<R, E> {
    return combine(
        result1 = result1,
        result2 = result2,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, E> Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    transformValues: (T1, T2, T3) -> R,
): DataResult<R, E> {
    return combine(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, E> Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    transformValues: (T1, T2, T3, T4) -> R,
): DataResult<R, E> {
    return combine(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, T5, E> Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    transformValues: (T1, T2, T3, T4, T5) -> R,
): DataResult<R, E> {
    return combine(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, T5, T6, E> Factory.combine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    transformValues: (T1, T2, T3, T4, T5, T6) -> R,
): DataResult<R, E> {
    return combine(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        result6 = result6,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> Factory.combine(
    results: Iterable<DataResult<T, E>>,
    transformValues: (List<T>) -> R,
): DataResult<R, E> {
    return combine(
        results = results,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> Factory.combine(
    vararg results: DataResult<T, E>,
    transformValues: (List<T>) -> R,
): DataResult<R, E> {
    return combine(
        results = results,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}
//endregion

//region flatCombine
public inline fun <R, F, T1, T2, E : F> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    transformValues: Factory.(T1, T2) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatCombine(
        result1 = result1,
        result2 = result2,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    transformValues: Factory.(T1, T2, T3) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatCombine(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    transformValues: Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatCombine(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    transformValues: Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatCombine(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E : F> Factory.flatCombine(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    transformValues: Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatCombine(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        result6 = result6,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Factory.flatCombine(
    results: Iterable<DataResult<T, E>>,
    transformValues: Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatCombine(
        results = results,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Factory.flatCombine(
    vararg results: DataResult<T, E>,
    transformValues: Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatCombine(
        results = results,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}
//endregion
