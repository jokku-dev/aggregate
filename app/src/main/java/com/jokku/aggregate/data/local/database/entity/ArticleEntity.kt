package com.jokku.aggregate.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.jokku.aggregate.data.local.database.DatabaseConstants.ARTICLES
import com.jokku.aggregate.data.local.database.DatabaseConstants.ARTICLE_SOURCE_
import com.jokku.aggregate.data.mapper.FromEntityMapper
import com.jokku.aggregate.data.repo.model.Article
import com.jokku.aggregate.data.repo.model.ArticleSource

@Entity(
    tableName = ARTICLES,
    foreignKeys = [
        ForeignKey(
            entity = ArticlesResponseEntity::class,
            parentColumns = ["id"],
            childColumns = ["articles_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
            )
    ]
)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "articles_id")
    val articlesId: Long,
    val author: String,
    val content: String,
    val description: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    @Embedded(prefix = ARTICLE_SOURCE_)
    val source: ArticleEntitySource,
    val title: String,
    @ColumnInfo(index = true)
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

data class ArticleEntitySource(
    val id: String,
    val name: String
) : FromEntityMapper<ArticleSource> {
    override fun asExternalModel() = ArticleSource(
        id = id,
        name = name
    )
}