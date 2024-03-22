package ua.railian.data.result.merge

import ua.railian.data.result.DataResult

//region flatMerge
public inline fun <R, F, T1, T2, E : F> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    transformValues: DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMerge(
        result1 = result1,
        result2 = result2,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    transformValues: DataResult.Factory.(T1, T2, T3) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMerge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMerge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMerge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E : F> DataResult.Factory.flatMerge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMerge(
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

public inline fun <R, F, T, E : F> DataResult.Factory.flatMerge(
    results: Iterable<DataResult<T, E>>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMerge(
        results = results,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.flatMerge(
    vararg results: DataResult<T, E>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMerge(
        results = results,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Iterable<DataResult<T, E>>.flatMergeAll(
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatMerge(
        results = this,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Array<DataResult<T, E>>.flatMergeAll(
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatMerge(
        results = this,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}
//endregion

//region merge
public inline fun <R, T1, T2, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    transformValues: (T1, T2) -> R,
): DataResult<R, E> {
    return merge(
        result1 = result1,
        result2 = result2,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    transformValues: (T1, T2, T3) -> R,
): DataResult<R, E> {
    return merge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    transformValues: (T1, T2, T3, T4) -> R,
): DataResult<R, E> {
    return merge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, T5, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    transformValues: (T1, T2, T3, T4, T5) -> R,
): DataResult<R, E> {
    return merge(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, T5, T6, E> DataResult.Factory.merge(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    transformValues: (T1, T2, T3, T4, T5, T6) -> R,
): DataResult<R, E> {
    return merge(
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

public inline fun <R, T, E> DataResult.Factory.merge(
    results: Iterable<DataResult<T, E>>,
    transformValues: (List<T>) -> R,
): DataResult<R, E> {
    return DataResult.merge(
        results = results,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> DataResult.Factory.merge(
    vararg results: DataResult<T, E>,
    transformValues: (List<T>) -> R,
): DataResult<R, E> {
    return merge(
        results = results,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> Iterable<DataResult<T, E>>.mergeAll(
    transformValues: (List<T>) -> R,
): DataResult<R, E> {
    return DataResult.merge(
        results = this,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> Array<DataResult<T, E>>.mergeAll(
    transformValues: (List<T>) -> R,
): DataResult<R, E> {
    return DataResult.merge(
        results = this,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}
//endregion
