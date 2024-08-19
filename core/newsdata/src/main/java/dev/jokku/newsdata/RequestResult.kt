package dev.jokku.newsdata

/**
 * State Class for data retrieving success state
 */
sealed class RequestResult<out E>(internal val data: E? = null) {
    class InProgress<E>(data: E? = null) : RequestResult<E>(data)
    class Success<E: Any>(data: E) : RequestResult<E>(data)
    class Error<E>(data: E? = null) : RequestResult<E>(data)
}

/**
 * Maps request result's data into what is passed in mapper parameter
 */
internal fun <I, O> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O> {
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
internal fun <T> Result<T>.toRequestResult(): RequestResult<T> {
    return when {
        isSuccess -> RequestResult.Success(checkNotNull(getOrThrow()))
        isFailure -> RequestResult.Error()
        else -> error("Impossible branch")
    }
}