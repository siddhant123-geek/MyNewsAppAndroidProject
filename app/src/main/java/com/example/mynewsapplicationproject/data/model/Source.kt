package com.example.mynewsapplicationproject.data.model

import com.google.gson.annotations.SerializedName

data class Source (
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
)