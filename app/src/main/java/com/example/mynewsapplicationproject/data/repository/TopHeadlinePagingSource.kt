package com.example.mynewsapplicationproject.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mynewsapplicationproject.data.api.NetworkService
import com.example.mynewsapplicationproject.data.model.ApiArticle
import com.example.mynewsapplicationproject.utils.AppConstant.COUNTRY
import com.example.mynewsapplicationproject.utils.AppConstant.INITIAL_PAGE
import com.example.mynewsapplicationproject.utils.AppConstant.PAGE_SIZE

class TopHeadlinePagingSource(private val networkService: NetworkService) :
    PagingSource<Int, ApiArticle>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiArticle> {
        return try {
            val page = params.key ?: INITIAL_PAGE

            val response = networkService.getTopHeadlines(
                country = COUNTRY,
                page = page,
                pageSize = PAGE_SIZE
            )

            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == INITIAL_PAGE) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ApiArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}