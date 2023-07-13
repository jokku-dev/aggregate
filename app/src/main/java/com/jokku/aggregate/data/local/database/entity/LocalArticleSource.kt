package com.jokku.aggregate.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.database.DatabaseConstants.ARTICLE_SOURCES
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.ArticleSource

@Entity(tableName = ARTICLE_SOURCES)
data class LocalArticleSource(
    @PrimaryKey
    val id: String,
    val name: String
) : DataModelMapper<ArticleSource> {
    override fun map() = ArticleSource(
        id = id,
        name = name
    )
}
