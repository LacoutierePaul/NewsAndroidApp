package com.example.test_github.Utils

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.test_github.Article
import com.example.test_github.ArticleDB
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {

    //@Upsert possible
    @Upsert
    suspend fun upsertArticle(article: ArticleDB)

    @Upsert
    suspend fun upsertAllArticle(vararg article: ArticleDB)

    @Delete
    suspend fun deleteArticle(article: ArticleDB)

    @Delete
    suspend fun deleteAllArticle(vararg article: ArticleDB)

    @Query("SELECT * FROM articles")
    fun getArticles(): MutableList<ArticleDB> // Flow notify et fournit une nouvelle liste
// qd source modifiée (donnée insérée sd base)

    @Query("SELECT * FROM articles WHERE category = :category and language = :language")
    fun getArticlesCategoryLanguage(category: String, language: String): MutableList<ArticleDB>
}