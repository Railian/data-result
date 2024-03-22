package ua.railian.data.result.merge

import kotlinx.coroutines.flow.Flow
import ua.railian.data.result.DataResult

//region flatMerge
public inline fun <R, F, T1, T2, E : F> DataResult.Factory.flatMerge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMerge(
        flow1 = flow1,
        flow2 = flow2,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> DataResult.Factory.flatMerge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMerge(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> DataResult.Factory.flatMerge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMerge(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> DataResult.Factory.flatMerge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMerge(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.flatMerge(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMerge(
        flows = flows,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.flatMerge(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMerge(
        flows = flows,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Iterable<Flow<DataResult<T, E>>>.flatMergeAll(
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeAll(
        transformErrors = { ua.railian.data.result.DataResult.failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Array<Flow<DataResult<T, E>>>.flatMergeAll(
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeAll(
        transformErrors = { ua.railian.data.result.DataResult.failure(it.first()) },
        transformValues = transformValues,
    )
}
//endregion

//region flatMergeCatching
public inline fun <R, F, T1, T2, E : F> DataResult.Factory.flatMergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeCatching(
        flow1 = flow1,
        flow2 = flow2,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> DataResult.Factory.flatMergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> DataResult.Factory.flatMergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> DataResult.Factory.flatMergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.flatMergeCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.flatMergeCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Iterable<Flow<DataResult<T, E>>>.flatMergeAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeAllCatching(
        handleException = handleException,
        transformErrors = { ua.railian.data.result.DataResult.failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Array<Flow<DataResult<T, E>>>.flatMergeAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend DataResult.Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatMergeAllCatching(
        handleException = handleException,
        transformErrors = { ua.railian.data.result.DataResult.failure(it.first()) },
        transformValues = transformValues,
    )
}
//endregion

//region merge
public inline fun <R, T1, T2, E> DataResult.Factory.merge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline transformValues: suspend (T1, T2) -> R,
): Flow<DataResult<R, E>> {
    return merge(
        flow1 = flow1,
        flow2 = flow2,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, E> DataResult.Factory.merge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline transformValues: suspend (T1, T2, T3) -> R,
): Flow<DataResult<R, E>> {
    return merge(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, E> DataResult.Factory.merge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline transformValues: suspend (T1, T2, T3, T4) -> R,
): Flow<DataResult<R, E>> {
    return merge(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, T5, E> DataResult.Factory.merge(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5) -> R,
): Flow<DataResult<R, E>> {
    return merge(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> DataResult.Factory.merge(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, E>> {
    return merge(
        flows = flows,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> DataResult.Factory.merge(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, E>> {
    return merge(
        flows = flows,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> Iterable<Flow<DataResult<T, E>>>.mergeAll(
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, E>> {
    return mergeAll(
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> Array<Flow<DataResult<T, E>>>.mergeAll(
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, E>> {
    return mergeAll(
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}
//endregion

//region mergeCatching
public inline fun <R, F, T1, T2, E : F> DataResult.Factory.mergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2) -> R,
): Flow<DataResult<R, F>> {
    return mergeCatching(
        flow1 = flow1,
        flow2 = flow2,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> DataResult.Factory.mergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3) -> R,
): Flow<DataResult<R, F>> {
    return mergeCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> DataResult.Factory.mergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3, T4) -> R,
): Flow<DataResult<R, F>> {
    return mergeCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> DataResult.Factory.mergeCatching(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5) -> R,
): Flow<DataResult<R, F>> {
    return mergeCatching(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.mergeCatching(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return mergeCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> DataResult.Factory.mergeCatching(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return mergeCatching(
        flows = flows,
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Iterable<Flow<DataResult<T, E>>>.mergeAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return mergeAllCatching(
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Array<Flow<DataResult<T, E>>>.mergeAllCatching(
    crossinline handleException: suspend DataResult.Factory.(Throwable) -> DataResult<R, F>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, F>> {
    return mergeAllCatching(
        handleException = handleException,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}
//endregion
