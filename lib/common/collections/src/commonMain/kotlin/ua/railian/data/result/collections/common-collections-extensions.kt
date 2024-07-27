package ua.railian.data.result.collections

import ua.railian.data.result.DataResult
import ua.railian.data.result.errorOrNull
import ua.railian.data.result.getOrNull

//region Filters
/**
 * Filters a collection of [DataResult] objects, returning a list
 * containing only the successful results.
 */
public fun <T> Iterable<DataResult<T, *>>.filterIsSuccess(
): List<DataResult.Success<T, Nothing>> {
    return filterIsInstance<DataResult.Success<T, Nothing>>()
}

/**
 * Filters an array of [DataResult] objects, returning a list
 * containing only the successful results.
 */
public fun <T> Array<DataResult<T, *>>.filterIsSuccess(
): List<DataResult.Success<T, Nothing>> {
    return filterIsInstance<DataResult.Success<T, Nothing>>()
}

/**
 * Filters a collection of [DataResult] objects, returning a list
 * containing only the failed results.
 */
public fun <E> Iterable<DataResult<*, E>>.filterIsFailure(
): List<DataResult.Failure<Nothing, E>> {
    return filterIsInstance<DataResult.Failure<Nothing, E>>()
}

/**
 * Filters an array of [DataResult] objects, returning a list
 * containing only the failed results.
 */
public fun <E> Array<DataResult<*, E>>.filterIsFailure(
): List<DataResult.Failure<Nothing, E>> {
    return filterIsInstance<DataResult.Failure<Nothing, E>>()
}
//endregion

//region Extractions
/**
 * Extracts the values from a collection of [DataResult.Success] objects.
 */
public inline val <T> Iterable<DataResult<T, *>>.values: List<T>
    get() = mapNotNull(DataResult<T, *>::getOrNull)

/**
 * Extracts the values from an array of [DataResult.Success] objects.
 */
public inline val <T> Array<DataResult<T, *>>.values: List<T>
    get() = mapNotNull(DataResult<T, *>::getOrNull)

/**
 * Extracts the errors from a collection of [DataResult.Failure] objects.
 */
public inline val <E> Iterable<DataResult<*, E>>.errors: List<E>
    get() = mapNotNull(DataResult<*, E>::errorOrNull)

/**
 * Extracts the errors from an array of [DataResult.Failure] objects.
 */
public inline val <E> Array<DataResult<*, E>>.errors: List<E>
    get() = mapNotNull(DataResult<*, E>::errorOrNull)
//endregion
