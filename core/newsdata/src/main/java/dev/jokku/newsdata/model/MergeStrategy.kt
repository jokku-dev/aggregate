package dev.jokku.newsdata.model

import dev.jokku.newsdata.RequestResult

interface MergeStrategy<E> {

    fun merge(left: E, right: E): E
}

internal class RequestResponseMergeStrategy<T> : MergeStrategy<RequestResult<T>> {
    override fun merge(
        left: RequestResult<T>,
        right: RequestResult<T>
    ): RequestResult<T> {
        TODO("Not yet implemented")
    }

}