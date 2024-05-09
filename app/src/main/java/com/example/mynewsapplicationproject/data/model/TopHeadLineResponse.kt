package com.example.mynewsapplicationproject.data.model

import com.google.gson.annotations.SerializedName

data class TopHeadLineResponse (
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles:List<ApiArticle> = ArrayList(),
)