package dev.aggregate.data.util

import dev.aggregate.data.RequestResult
import dev.aggregate.data.RequestResult.Error
import dev.aggregate.data.RequestResult.InProgress
import dev.aggregate.data.RequestResult.Success

interface MergeStrategy<E> {

    fun merge(right: E, left: E): E
}

/**
 * Strategy of what state to pass depending on the result of receiving data
 */
internal class RequestResponseMergeStrategy<T : Any> : MergeStrategy<RequestResult<T>> {

    override fun merge(
        right: RequestResult<T>,
        left: RequestResult<T>
    ): RequestResult<T> {
        return when {
            right is InProgress && left is InProgress -> merge(right, left)
            right is Success && left is InProgress -> merge(right, left)
            right is InProgress && left is Success -> merge(right, left)
            right is Success && left is Success -> merge(right, left)
            right is Success && left is Error -> merge(right, left)
            right is InProgress && left is Error -> merge(right, left)
            right is Error && left is InProgress -> merge(right, left)
            right is Error && left is Success -> merge(right, left)

            else -> error("Unimplemented branch right=$right & left=$left")
        }
    }

    private fun merge(
        cache: InProgress<T>,
        network: InProgress<T>
    ): RequestResult<T> {
        return when {
            network.data != null -> InProgress(network.data)
            else -> InProgress(cache.data)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun merge(
        cache: Success<T>,
        network: InProgress<T>
    ): RequestResult<T> {
        return InProgress(cache.data)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun merge(
        cache: InProgress<T>,
        network: Success<T>
    ): RequestResult<T> {
        return InProgress(network.data)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun merge(
        cache: Success<T>,
        network: Success<T>
    ): RequestResult<T> {
        return Success(data = network.data)
    }

    private fun merge(
        cache: Success<T>,
        network: Error<T>
    ): RequestResult<T> {
        return Error(data = cache.data, error = network.error)
    }

    private fun merge(
        cache: InProgress<T>,
        network: Error<T>
    ): RequestResult<T> {
        return Error(data = network.data ?: cache.data, error = network.error)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun merge(
        cache: Error<T>,
        network: InProgress<T>
    ): RequestResult<T> {
        return network
    }

    @Suppress("UNUSED_PARAMETER")
    private fun merge(
        cache: Error<T>,
        network: Success<T>
    ): RequestResult<T> {
        return network
    }
}