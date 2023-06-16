package com.jokku.aggregate.data.remote.model

import com.jokku.aggregate.data.ResponseStatus
import com.jokku.aggregate.data.local.database.entity.LocalSourcesResponse
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.data.repo.model.SourcesResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteSourcesResponse(
    /**
     * Populated in the case of error status
     */
    @SerialName("code")
    val errorCode: String,
    /**
     * Populated in the case of error status
     */
    @SerialName("message")
    val errorMessage: String,
    /**
     * The results of the request.
     */
    @SerialName("sources")
    val sources: List<RemoteSource>,
    /**
     * If the request was successful or not. Options: ok, error. In the case of error a code and
     * message property will be populated.
     */
    @SerialName("status")
    val status: String
) : DataModelMapper<LocalSourcesResponse> {

    override fun map() = LocalSourcesResponse(
        sources = sources.mapList()
    )
}