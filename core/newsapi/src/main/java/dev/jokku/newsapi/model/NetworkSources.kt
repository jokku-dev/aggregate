package dev.jokku.newsapi.model

import dev.jokku.aggregate.data.local.database.entity.SourcesResponseEntity
import dev.jokku.aggregate.data.local.database.entity.intermediate.ResponseWithSources
import dev.jokku.aggregate.data.mapper.mapList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.ZoneOffset

@Serializable
data class NetworkSources(
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
    val sources: List<NetSource>,
    /**
     * If the request was successful or not. Options: ok, error. In the case of error a code and
     * message property will be populated.
     */
    @SerialName("status")
    val status: String
) : dev.jokku.aggregate.data.mapper.FromRemoteMapper<dev.jokku.aggregate.data.local.database.entity.intermediate.ResponseWithSources> {
    override fun asEntity() =
        dev.jokku.aggregate.data.local.database.entity.intermediate.ResponseWithSources(
            response = dev.jokku.aggregate.data.local.database.entity.SourcesResponseEntity(
                date = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
            ),
            sources = sources.mapList()
        )
}