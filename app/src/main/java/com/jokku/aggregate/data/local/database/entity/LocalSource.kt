package com.jokku.aggregate.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.database.DatabaseConstants.SOURCES
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.Source
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = SOURCES)
data class LocalSource(
    @PrimaryKey
    val id: String,
    val category: String,
    val country: String,
    val description: String,
    val language: String,
    val name: String,
    val url: String
) : DataModelMapper<Source> {
    override fun map() = Source(
        category = category,
        country = country,
        description = description,
        id = id,
        language = language,
        name = name,
        url = url
    )
}
