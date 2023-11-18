package com.example.test_github

data class MyDataItem( // quand on va sur le lien https://jsonplaceholder.typicode.com/posts
    // les datas sont sous la forme
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)