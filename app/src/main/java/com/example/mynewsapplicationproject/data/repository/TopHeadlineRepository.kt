package com.example.mynewsapplicationproject.data.repository

import com.example.mynewsapplicationproject.data.api.NetworkService
import com.example.mynewsapplicationproject.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
                emit(networkService.getTopHeadlines(country))
        }.map{
            it.articles
        }
    }
}