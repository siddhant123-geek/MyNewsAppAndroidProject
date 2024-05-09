package com.example.mynewsapplicationproject.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.mynewsapplicationproject.data.local.entity.Article
import com.example.mynewsapplicationproject.data.local.entity.Source
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllArticles(): Flow<List<Article>>

    @Insert
    fun insertAll(articles: List<Article>)

    @Query("DELETE FROM article")
    fun deleteAll()

    @Transaction
    fun deleteAndInsertAllArticles(articles: List<Article>) {
        deleteAll()
        insertAll(articles)
    }
}