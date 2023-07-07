package com.jokku.aggregate.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.database.DatabaseConstants.FAVORITE_TOP_HEADLINE_ARTICLES
import com.jokku.aggregate.data.local.database.DatabaseConstants.ARTICLE_SOURCE_
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.Article

@Entity(tableName = FAVORITE_TOP_HEADLINE_ARTICLES)
data class LocalTopHeadlinesArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded(prefix = ARTICLE_SOURCE_)
    val source: LocalArticleSource,
    val title: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String
) : DataModelMapper<Article> {
    override fun map() = Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.map(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}

