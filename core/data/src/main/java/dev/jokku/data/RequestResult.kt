package dev.jokku.data

/**
 * Provides current request state with data
 */
sealed class RequestResult<out E: Any>(open val data: E? = null) {
    class InProgress<E: Any>(data: E? = null) : RequestResult<E>(data)
    class Success<E: Any>(override val data: E) : RequestResult<E>(data)
    class Error<E: Any>(data: E? = null, val error: Throwable? = null) : RequestResult<E>(data)
}

/**
 * Maps request result's data into what is passed in mapper parameter
 */
fun <I: Any, O: Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
    return when (this) {
        is RequestResult.InProgress -> RequestResult.InProgress(data?.let(mapper))
        is RequestResult.Success -> {
            val outData: O = mapper(checkNotNull(data))
            RequestResult.Success(checkNotNull(outData))
        }
        is RequestResult.Error -> RequestResult.Error(data?.let(mapper))
    }
}

/**
 * Transforms Kotlin Result class to custom RequestResult
 */
internal fun <T: Any> Result<T>.toRequestResult(): RequestResult<T> {
    return when {
        isSuccess -> RequestResult.Success(checkNotNull(getOrThrow()))
        isFailure -> RequestResult.Error()
        else -> error("Impossible branch")
    }
}