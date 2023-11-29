package com.example.test_github

import retrofit2.Call
import retrofit2.http.GET

interface Apiinterface {
    //@GET("posts")
    //fun getData():Call<List<MyDataItem>>

    @GET("top-headlines?country=us&category=business&apiKey=150c1cb0b4e644e98f794f4d1a14be2e")

    fun getData():Call<MyDataItem>
}