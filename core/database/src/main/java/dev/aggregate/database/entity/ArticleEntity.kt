package dev.aggregate.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import dev.aggregate.database.utils.DatabaseConstants.ARTICLES
import dev.aggregate.database.utils.DatabaseConstants.ARTICLE_SOURCE_
import dev.aggregate.model.Article

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
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "articles_id") val articlesId: Long,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "published_at") val publishedAt: String?,
    @ColumnInfo(name = "source") @Embedded(prefix = ARTICLE_SOURCE_) val source: ArticleSourceEntity,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url", index = true) val url: String?,
    @ColumnInfo(name = "url_to_image") val urlToImage: String?,
)

@Entity
data class ArticleSourceEntity(
    @ColumnInfo(name = "id") val id: String?,
    @ColumnInfo(name = "name") val name: String?,
)

fun ArticleEntity.toArticle(): Article {
    TODO("Not Implemented")
}
