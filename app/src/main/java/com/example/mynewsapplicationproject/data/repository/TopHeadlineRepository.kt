package com.example.mynewsapplicationproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mynewsapplicationproject.data.api.NetworkService
import com.example.mynewsapplicationproject.data.local.MyAppDataBaseService
import com.example.mynewsapplicationproject.data.local.entity.Article
import com.example.mynewsapplicationproject.data.model.ApiArticle
import com.example.mynewsapplicationproject.data.model.toArticleEntity
import com.example.mynewsapplicationproject.utils.AppConstant.PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopHeadlineRepository @Inject constructor(
    private val networkService: NetworkService,
    private val dataBaseService: MyAppDataBaseService) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow { emit(networkService.getTopHeadlines(country)) }
            .flowOn(Dispatchers.IO)
            .map {
                it.articles.map { apiArticle -> apiArticle.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(dataBaseService.deleteAndInsertAllArticles((articles))) }
            }.flatMapConcat {
                dataBaseService.getArticles()
            }
    }

    fun getTopHeadlines(): Flow<PagingData<ApiArticle>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                TopHeadlinePagingSource(networkService)
            }
        ).flow
    }

    fun getArticlesDirectlyFromDB(): Flow<List<Article>> {
        return dataBaseService.getArticles()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getTopHeadlinesByLanguage(language: String): Flow<List<Article>> {
        return flow { emit(networkService.getTopHeadLinesByLanguage(language)) }
            .flowOn(Dispatchers.IO)
            .map {
                it.articles.map { apiArticle -> apiArticle.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(dataBaseService.deleteAndInsertAllArticles((articles))) }
            }.flatMapConcat {
                dataBaseService.getArticles()
            }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getTopHeadlinesByKeyword(keyword: String): Flow<List<Article>> {
        return flow { emit(networkService.getTopHeadlinesByKeyword(keyword)) }
            .flowOn(Dispatchers.IO)
            .map {
                it.articles.map { apiArticle -> apiArticle.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(dataBaseService.deleteAndInsertAllArticles((articles))) }
            }.flatMapConcat {
                dataBaseService.getArticles()
            }
    }
}