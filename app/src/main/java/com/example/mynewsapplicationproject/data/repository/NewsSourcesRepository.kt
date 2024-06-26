package com.example.mynewsapplicationproject.data.repository

import com.example.mynewsapplicationproject.data.api.NetworkService
import com.example.mynewsapplicationproject.data.local.MyAppDataBaseService
import com.example.mynewsapplicationproject.data.local.entity.Source
import com.example.mynewsapplicationproject.data.model.toSourceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsSourcesRepository @Inject constructor(private val networkService: NetworkService,
    private val databaseService: MyAppDataBaseService) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getNewsSources(): Flow<List<Source>> {
        return flow { emit(networkService.getNewsSource()) }
            .flowOn(Dispatchers.IO)
            .map {
                it.sources.map { apiSource -> apiSource.toSourceEntity() }
            }.flatMapConcat { sources ->
                flow { emit(databaseService.deleteAndInsertAllSources(sources)) }
            }.flatMapConcat {
                databaseService.getSources()
            }
    }

    fun getSourcesDirectlyFromDB(): Flow<List<Source>> {
        return databaseService.getSources()
    }
}