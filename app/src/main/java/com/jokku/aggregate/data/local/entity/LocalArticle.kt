package com.jokku.aggregate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.DatabaseConstants.ARTICLE
import com.jokku.aggregate.data.mapper.DataModelMapper
import com.jokku.aggregate.data.repo.model.Article

@Entity(tableName = ARTICLE)
data class LocalArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: LocalArticleSource,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String,
    val bookmarked: Boolean
) : DataModelMapper<Article> {
    override fun map(): Article {
        return Article(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            source = source.map(),
            title = title,
            url = url,
            urlToImage = urlToImage,
            bookmarked = bookmarked
        )
    }
}

