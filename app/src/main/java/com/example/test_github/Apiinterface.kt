package com.example.test_github

import com.example.test_github.MyDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiinterface {
   // @GET("top-headlines?country=us&category=business&apiKey=150c1cb0b4e644e98f794f4d1a14be2e")
    //fun getData():Call<MyDataItem>
   @GET("top-headlines")
   fun getData(@Query("country") country: String, @Query("category") category: String, @Query("apiKey") apiKey: String): Call<MyDataItem>
}