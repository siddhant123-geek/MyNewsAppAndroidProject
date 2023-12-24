package com.example.mynewsapplicationproject.data.repository

import com.example.mynewsapplicationproject.data.api.NetworkService
import com.example.mynewsapplicationproject.data.model.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsSourcesRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsSources(): Flow<List<Source>> {
        return flow {
            emit(networkService.getNewsSource())
        }.map {it.sources}
    }
}