package com.example.test_github.Utils

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.test_github.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {

    //@Upsert possible
    @Upsert
    suspend fun upsertArticle(article: Article)

    @Upsert
    suspend fun upsertAllArticle(vararg article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Delete
    suspend fun deleteAllArticle(vararg article: Article)

    @Query("SELECT * FROM articles")
    fun getArticles(): Flow<List<Article>> // Flow notify et fournit une nouvelle liste
// qd source modifiée (donnée insérée sd base)
}