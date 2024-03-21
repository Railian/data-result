package ua.railian.data.result

public fun <T> Iterable<DataResult<T, *>>.filterIsSuccess(): List<DataResult.Success<T, Nothing>> {
    return filterIsInstance<DataResult.Success<T, Nothing>>()
}

public fun <T> Array<DataResult<T, *>>.filterIsSuccess(): List<DataResult.Success<T, Nothing>> {
    return filterIsInstance<DataResult.Success<T, Nothing>>()
}

public fun <E> Iterable<DataResult<*, E>>.filterIsFailure(): List<DataResult.Failure<Nothing, E>> {
    return filterIsInstance<DataResult.Failure<Nothing, E>>()
}

public fun <E> Array<DataResult<*, E>>.filterIsFailure(): List<DataResult.Failure<Nothing, E>> {
    return filterIsInstance<DataResult.Failure<Nothing, E>>()
}

public inline val <T> Iterable<DataResult.Success<T, *>>.values: List<T>
    get() = map(DataResult.Success<T, *>::value)

public inline val <T> Array<DataResult.Success<T, *>>.values: List<T>
    get() = map(DataResult.Success<T, *>::value)

public inline val <E> Iterable<DataResult.Failure<*, E>>.errors: List<E>
    get() = map(DataResult.Failure<*, E>::error)

public inline val <E> Array<DataResult.Failure<*, E>>.errors: List<E>
    get() = map(DataResult.Failure<*, E>::error)
