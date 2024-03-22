package ua.railian.data.result.safe.merge

import ua.railian.data.result.DataResult

//region flatMergeCatching
public inline fun <R, F, T1, T2, E : F> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMergeCatching(
        result1 = result1,
        result2 = result2,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMergeCatching(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMergeCatching(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMergeCatching(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E : F> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMergeCatching(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        result6 = result6,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.flatMergeCatching(
    results: Iterable<DataResult<T, E>>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMergeCatching(
        results = results,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.flatMergeCatching(
    vararg results: DataResult<T, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return flatMergeCatching(
        results = results,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Iterable<DataResult<T, E>>.flatMergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatMergeCatching(
        results = this,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Array<DataResult<T, E>>.flatMergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.flatMergeCatching(
        results = this,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}
//endregion


//region mergeCatching
public inline fun <R, F, T1, T2, E : F> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: (T1, T2) -> R,
): DataResult<R, F> {
    return mergeCatching(
        result1 = result1,
        result2 = result2,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: (T1, T2, T3) -> R,
): DataResult<R, F> {
    return mergeCatching(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: (T1, T2, T3, T4) -> R,
): DataResult<R, F> {
    return mergeCatching(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: (T1, T2, T3, T4, T5) -> R,
): DataResult<R, F> {
    return mergeCatching(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E : F> DataResult.Factory.mergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    result3: DataResult<T3, E>,
    result4: DataResult<T4, E>,
    result5: DataResult<T5, E>,
    result6: DataResult<T6, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: (T1, T2, T3, T4, T5, T6) -> R,
): DataResult<R, F> {
    return mergeCatching(
        result1 = result1,
        result2 = result2,
        result3 = result3,
        result4 = result4,
        result5 = result5,
        result6 = result6,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.mergeCatching(
    results: Iterable<DataResult<T, E>>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.mergeCatching(
        results = results,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.mergeCatching(
    vararg results: DataResult<T, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return mergeCatching(
        results = results,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Iterable<DataResult<T, E>>.mergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.mergeCatching(
        results = this,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Array<DataResult<T, E>>.mergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.mergeCatching(
        results = this,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}
//endregion
