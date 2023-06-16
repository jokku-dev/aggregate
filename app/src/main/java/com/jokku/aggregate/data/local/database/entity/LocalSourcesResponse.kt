package com.jokku.aggregate.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jokku.aggregate.data.local.database.DatabaseConstants.SOURCES_RESPONSES
import com.jokku.aggregate.data.local.database.ListConverter
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.mapper.mapList
import com.jokku.aggregate.data.repo.model.SourcesResponse

@Entity(tableName = SOURCES_RESPONSES)
@TypeConverters(ListConverter::class)
data class LocalSourcesResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sources: List<LocalSource>
) : DataModelMapper<SourcesResponse.OkSourcesResponse> {

    override fun map() = SourcesResponse.OkSourcesResponse(
        sources = sources.mapList()
    )
}
