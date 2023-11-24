package com.example.test_github

import retrofit2.Call
import retrofit2.http.GET

interface Apiinterface {
    //@GET("posts")
    //fun getData():Call<List<MyDataItem>>

    @GET("everything?q=tesla&from=2023-10-24&sortBy=publishedAt&apiKey=150c1cb0b4e644e98f794f4d1a14be2e")
    fun getData():Call<MyDataItem>
}