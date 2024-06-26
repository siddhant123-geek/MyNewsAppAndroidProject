package com.example.mynewsapplicationproject.data.local

import com.example.mynewsapplicationproject.data.local.entity.Article
import com.example.mynewsapplicationproject.data.local.entity.Source
import kotlinx.coroutines.flow.Flow

interface DataBaseService {

    fun getArticles(): Flow<List<Article>>

    fun deleteAndInsertAllArticles(articles: List<Article>)

    fun getSources(): Flow<List<Source>>

    fun deleteAndInsertAllSources(sources: List<Source>)
}