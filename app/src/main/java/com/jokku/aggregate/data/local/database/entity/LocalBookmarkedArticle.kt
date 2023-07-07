package com.jokku.aggregate.data.local.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.database.DatabaseConstants
import com.jokku.aggregate.data.local.database.DatabaseConstants.BOOKMARKED_ARTICLES
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.Article

@Entity(tableName = BOOKMARKED_ARTICLES)
data class LocalBookmarkedArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded(prefix = DatabaseConstants.ARTICLE_SOURCE_)
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