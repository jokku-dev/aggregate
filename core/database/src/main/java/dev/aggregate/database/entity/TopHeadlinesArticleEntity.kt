package dev.aggregate.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.aggregate.database.utils.DatabaseConstants.ARTICLE_SOURCE_
import dev.aggregate.database.utils.DatabaseConstants.TOP_HEADLINES_ARTICLES

@Entity(
    tableName = TOP_HEADLINES_ARTICLES
)
data class TopHeadlinesArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "published_at") val publishedAt: String?,
    @ColumnInfo(name = "source") @Embedded(prefix = ARTICLE_SOURCE_) val source: ArticleSource,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "url_to_image") val urlToImage: String?,
)
