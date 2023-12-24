package com.example.mynewsapplicationproject.data.model

import com.google.gson.annotations.SerializedName

data class ContentToSee(
    @SerializedName("text")
    val text: String = "")
