package com.example.test_github

import com.example.test_github.Utils.ArticleDAO

class ArticleDAOImpl : ArticleDAO {
    override suspend fun upsertArticle(article: ArticleDB) {
        TODO("Not yet implemented")
    }

    override suspend fun upsertAllArticle(vararg article: ArticleDB) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(article: ArticleDB) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllArticle(vararg article: ArticleDB) {
        TODO("Not yet implemented")
    }

    override fun getArticles(): MutableList<ArticleDB> {

        val articles: MutableList<ArticleDB> = mutableListOf(
            ArticleDB(Source("test","test"), "test","test","test","test","test","test","test","test","test"),
            ArticleDB(Source("test1","test1"), "test1","test1","test1","test1","test1","test1","test1","test1","test1")
        )
        return articles
    }

    override fun getArticlesCategoryLanguage(
        category: String,
        language: String
    ): MutableList<ArticleDB> {
        val articles: MutableList<ArticleDB> = mutableListOf(
            ArticleDB(Source("test","test"), "test","test","test","test","test","test","test","test","test"),
            ArticleDB(Source("test1","test1"), "test1","test1","test1","test1","test1","test1","test1","test1","test1")
        )
        return articles
    }

    override fun getCategories(): MutableList<String> {
        TODO("Not yet implemented")
    }

    override fun getLanguage(): MutableList<String> {
        TODO("Not yet implemented")
    }
}