package com.jokku.aggregate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.DatabaseConstants.ARTICLE_SOURCE
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.ArticleSource

@Entity(tableName = ARTICLE_SOURCE)
data class LocalArticleSource(
    @PrimaryKey val id: String,
    val name: String
) : DataModelMapper<ArticleSource> {
    override fun map(): ArticleSource {
        return ArticleSource(
            id = id, name = name
        )
    }
}
