package ua.railian.data.result.coroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import ua.railian.data.result.DataResult

public interface DataResultFlowCollector<in T, in E>
    : FlowCollector<DataResult<T, E>> {
    public suspend fun emitSuccess(value: T)
    public suspend fun emitFailure(error: E)
}

public suspend fun <T> DataResultFlowCollector<T, *>.emitAllSuccess(flow: Flow<T>) {
    emitAll(flow.map(DataResult.Factory::success))
}

public suspend fun <E> DataResultFlowCollector<*, E>.emitAllFailure(flow: Flow<E>) {
    emitAll(flow.map(DataResult.Factory::failure))
}
