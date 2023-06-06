package com.jokku.aggregate.data.local.entity

import androidx.room.Entity
import com.jokku.aggregate.data.local.DatabaseConstants.SOURCES_RESPONSE
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.data.remote.model.RemoteSource
import com.jokku.aggregate.data.repo.model.SourcesResponse

@Entity(tableName = SOURCES_RESPONSE)
data class LocalSourcesResponse(
    val sources: List<RemoteSource>
) : DataModelMapper<SourcesResponse.OkSourcesResponse> {
    override fun map(): SourcesResponse.OkSourcesResponse {
        return SourcesResponse.OkSourcesResponse(
            sources = sources.mapList()
        )
    }
}
