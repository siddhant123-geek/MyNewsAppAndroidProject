package com.example.mynewsapplicationproject.ui.topHeadlinesWithPaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.mynewsapplicationproject.data.local.entity.Article
import com.example.mynewsapplicationproject.data.model.ApiArticle
import com.example.mynewsapplicationproject.data.repository.TopHeadlineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlinesWithPagingViewModel @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<ApiArticle>>(value = PagingData.empty())

    val uiState: StateFlow<PagingData<ApiArticle>> = _uiState

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines()
                .collect {
                    _uiState.value = it
                }
        }
    }

}