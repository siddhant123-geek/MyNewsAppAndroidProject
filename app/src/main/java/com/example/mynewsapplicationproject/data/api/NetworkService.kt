package com.example.mynewsapplicationproject.data.api

import com.example.mynewsapplicationproject.data.model.NewsSourcesResponse
import com.example.mynewsapplicationproject.data.model.TopHeadLineResponse
import com.example.mynewsapplicationproject.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadLineResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadLinesByLanguage(@Query("language") language: String): TopHeadLineResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSource(): NewsSourcesResponse
}