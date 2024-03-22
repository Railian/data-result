package ua.railian.data.result.safe.merge

import ua.railian.data.result.DataResult
import ua.railian.data.result.merge.flatMerge
import ua.railian.data.result.merge.merge
import ua.railian.data.result.safe.buildCatching

//region flatMergeCatching
public inline fun <R, F, T1, T2, E> DataResult.Factory.flatMergeCatching(
    result1: DataResult<T1, E>,
    result2: DataResult<T2, E>,
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
        flatMerge(
            results = this@flatMergeAllCatching,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> Array<DataResult<T, E>>.flatMergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: DataResult.Factory.(List<E>) -> DataResult<R, F>,
    transformValues: DataResult.Factory.(List<T>) -> DataResult<R, F>,
): DataResult<R, F> {
    return DataResult.buildCatching(handleException) {
        flatMerge(
            results = this@flatMergeAllCatching,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
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
    return DataResult.buildCatching(handleException) {
        merge(
            results = this@mergeAllCatching,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}

public inline fun <R, F, T, E> Array<DataResult<T, E>>.mergeAllCatching(
    handleException: DataResult.Factory.(Throwable) -> DataResult<R, F>,
    transformErrors: (List<E>) -> F,
    transformValues: (List<T>) -> R,
): DataResult<R, F> {
    return DataResult.buildCatching(handleException) {
        merge(
            results = this@mergeAllCatching,
            transformErrors = transformErrors,
            transformValues = transformValues,
        )
    }
}
//endregion
