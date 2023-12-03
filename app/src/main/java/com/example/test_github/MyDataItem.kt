package com.example.test_github

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MyDataItem( // quand on va sur le lien https://jsonplaceholder.typicode.com/posts
    // les datas sont sous la forme
    /*
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int

     */

    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>
)

@Entity(tableName="articles")
data class Article(

    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
data class Source(
    val sourceId: String,
    val name: String
)
