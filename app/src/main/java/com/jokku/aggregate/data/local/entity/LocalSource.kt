package com.jokku.aggregate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.DatabaseConstants.SOURCE
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.Source

@Entity(tableName = SOURCE)
data class LocalSource(
    val category: String,
    val country: String,
    val description: String,
    @PrimaryKey val id: String,
    val language: String,
    val name: String,
    val url: String
) : DataModelMapper<Source> {
    override fun map(): Source {
        return Source(
            category = category,
            country = country,
            description = description,
            id = id,
            language = language,
            name = name,
            url = url
        )
    }
}
