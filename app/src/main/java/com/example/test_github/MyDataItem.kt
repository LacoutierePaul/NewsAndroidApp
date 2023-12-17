package com.example.test_github

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

data class MyDataItem(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>
)

@Entity(tableName="articles")
data class ArticleDB(

    val source: Source?,
    val author: String?,

    val title: String?,
    val description: String?,

    @PrimaryKey(autoGenerate = false)
    val url: String,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    val category: String?,
    val language: String?
) {
    companion object {
        operator fun invoke(
            source: Source?,
            author: String?,
            title: String?,
            description: String?,
            url: String,
            urlToImage: String?,
            publishedAt: String,
            content: String?,
            category: String?,
            language: String?

        ): ArticleDB {

            Log.i("invoke ArticleDB", ""+source+";"+author+";"+title+";"+description+";"+url+";"+urlToImage+";"+publishedAt+";"+content+";"+category+";"+language+";")
            return ArticleDB(

                source ?: Source("default", "default"),
                author ?: "default",
                title ?: "default",
                description ?: "default",
                url ?: "default",
                urlToImage ?: "default",
                publishedAt  ?: "default",
                content ?: "default",
                category ?: "default",
                language ?: "default",

                )
        }

        fun toArticleList(articleDBList: MutableList<ArticleDB>): MutableList<Article>
        {
            return articleDBList.map { Article( it.source ?: Source("default", "default"), it.author ?: "default", it.title ?: "default", it.description ?: "default", it.url ?: "default", it.urlToImage ?: "default", it.publishedAt ?: "default", it.content ?: "default") }.toMutableList()
        }
    }
}

data class Article(

    val source: Source?,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String

) {
    companion object{
        fun toArticleDBList(articleList: MutableList<Article>, category: String, language: String): MutableList<ArticleDB>
        {
            return articleList.map { ArticleDB(it.source, it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt, it.content, category, language) }.toMutableList()
        }
    }
}

data class Source(
    val sourceId: String,
    val name: String
)
