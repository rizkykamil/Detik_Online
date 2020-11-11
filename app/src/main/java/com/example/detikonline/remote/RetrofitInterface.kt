package com.example.detikonline.remote

import com.example.detikonline.remote.pojo.ResponseNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("v2/top-headlines")
    suspend fun topHeadlines(@Query("country") country: String
    ): Response<ResponseNews>
}