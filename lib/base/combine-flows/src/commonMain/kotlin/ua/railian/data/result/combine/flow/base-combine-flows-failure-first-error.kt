package ua.railian.data.result.combine.flow

import kotlinx.coroutines.flow.Flow
import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.failure

//region combineFlows
public inline fun <R, T1, T2, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline transformValues: suspend (T1, T2) -> R,
): Flow<DataResult<R, E>> {
    return combineFlows(
        flow1 = flow1,
        flow2 = flow2,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline transformValues: suspend (T1, T2, T3) -> R,
): Flow<DataResult<R, E>> {
    return combineFlows(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline transformValues: suspend (T1, T2, T3, T4) -> R,
): Flow<DataResult<R, E>> {
    return combineFlows(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, T5, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5) -> R,
): Flow<DataResult<R, E>> {
    return combineFlows(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T1, T2, T3, T4, T5, T6, E> Factory.combineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline transformValues: suspend (T1, T2, T3, T4, T5, T6) -> R,
): Flow<DataResult<R, E>> {
    return combineFlows(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        flow6 = flow6,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> Factory.combineFlows(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, E>> {
    return combineFlows(
        flows = flows,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}

public inline fun <R, T, E> Factory.combineFlows(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline transformValues: suspend (List<T>) -> R,
): Flow<DataResult<R, E>> {
    return combineFlows(
        flows = flows,
        transformErrors = List<E>::first,
        transformValues = transformValues,
    )
}
//endregion

//region flatCombineFlows
public inline fun <R, F, T1, T2, E : F> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    crossinline transformValues: suspend Factory.(T1, T2) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlows(
        flow1 = flow1,
        flow2 = flow2,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, E : F> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    crossinline transformValues: suspend Factory.(T1, T2, T3) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlows(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, E : F> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlows(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, E : F> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4, T5) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlows(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T1, T2, T3, T4, T5, T6, E : F> Factory.flatCombineFlows(
    flow1: Flow<DataResult<T1, E>>,
    flow2: Flow<DataResult<T2, E>>,
    flow3: Flow<DataResult<T3, E>>,
    flow4: Flow<DataResult<T4, E>>,
    flow5: Flow<DataResult<T5, E>>,
    flow6: Flow<DataResult<T6, E>>,
    crossinline transformValues: suspend Factory.(T1, T2, T3, T4, T5, T6) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlows(
        flow1 = flow1,
        flow2 = flow2,
        flow3 = flow3,
        flow4 = flow4,
        flow5 = flow5,
        flow6 = flow6,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Factory.flatCombineFlows(
    flows: Iterable<Flow<DataResult<T, E>>>,
    crossinline transformValues: suspend Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlows(
        flows = flows,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}

public inline fun <R, F, T, E : F> Factory.flatCombineFlows(
    vararg flows: Flow<DataResult<T, E>>,
    crossinline transformValues: suspend Factory.(List<T>) -> DataResult<R, F>,
): Flow<DataResult<R, F>> {
    return flatCombineFlows(
        flows = flows,
        transformErrors = { failure(it.first()) },
        transformValues = transformValues,
    )
}
//endregion
