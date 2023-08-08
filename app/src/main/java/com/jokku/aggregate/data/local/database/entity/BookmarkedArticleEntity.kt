package com.jokku.aggregate.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.database.DatabaseConstants.ARTICLE_SOURCE_
import com.jokku.aggregate.data.local.database.DatabaseConstants.BOOKMARKED_ARTICLES
import com.jokku.aggregate.data.mapper.FromEntityMapper
import com.jokku.aggregate.data.repo.model.Article

@Entity(tableName = BOOKMARKED_ARTICLES)
data class BookmarkedArticleEntity(
    val author: String,
    val content: String,
    val description: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    @Embedded(prefix = ARTICLE_SOURCE_)
    val source: ArticleEntitySource,
    val title: String,
    @PrimaryKey
    val url: String,
    @ColumnInfo(name = "url_to_image")
    val urlToImage: String
) : FromEntityMapper<Article> {
    override fun asExternalModel() = Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.asExternalModel(),
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}