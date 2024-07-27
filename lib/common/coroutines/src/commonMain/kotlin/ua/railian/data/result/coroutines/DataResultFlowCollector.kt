package ua.railian.data.result.coroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import ua.railian.data.result.DataResult
import ua.railian.data.result.DataResult.Factory
import ua.railian.data.result.failure
import ua.railian.data.result.success

/**
 * Collector for emitting values of type [DataResult] to a flow.
 */
public interface DataResultFlowCollector<in T, in E>
    : FlowCollector<DataResult<T, E>>

/**
 * Emits a [DataResult.Success] instance with the given [value] to the collector.
 */
public suspend fun <T> DataResultFlowCollector<T, *>.emitSuccess(value: T) {
    emit(Factory.success(value))
}

/**
 * Emits a [DataResult.Failure] instance with the given [error] to the collector.
 */
public suspend fun <E> DataResultFlowCollector<*, E>.emitFailure(error: E) {
    emit(Factory.failure(error))
}

/**
 * Emits all values from the given [flow] transformed to [DataResult.Success]
 * into this [DataResultFlowCollector].
 */
public suspend fun <T> DataResultFlowCollector<T, *>.emitAllSuccess(flow: Flow<T>) {
    emitAll(flow.map(Factory::success))
}

/**
 * Emits all values from the given [flow] transformed to [DataResult.Failure]
 * into this [DataResultFlowCollector].
 */
public suspend fun <E> DataResultFlowCollector<*, E>.emitAllFailure(flow: Flow<E>) {
    emitAll(flow.map(Factory::failure))
}

/**
 *  Emits all values from the given [flow] using the provided [transform] function
 *  to convert each value to a [DataResult] before emitting it
 *  into this [DataResultFlowCollector].
 */
public suspend inline fun <R, T, E> DataResultFlowCollector<R, E>.emitAll(
    flow: Flow<T>,
    crossinline transform: suspend Factory.(value: T) -> DataResult<R, E>,
) {
    emitAll(flow.map { Factory.transform(it) })
}
