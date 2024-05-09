package com.example.mynewsapplicationproject.data.model

import android.util.Log
import com.example.mynewsapplicationproject.data.local.entity.Source
import com.google.gson.annotations.SerializedName

data class ApiSource (
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
)

fun ApiSource.toSourceEntity(): Source {
    Log.d("###", "toSourceEntity: id " + this.id)
    Log.d("###", "toSourceEntity: name " + this.name)
    Log.d("###", "toSourceEntity: name " + this.url)
    return Source(url = url, name = name)
}