package com.example.mynewsapplicationproject.ui.newsbylanguage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapplicationproject.data.model.Article
import com.example.mynewsapplicationproject.data.repository.TopHeadlineRepository
import com.example.mynewsapplicationproject.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsByLanguageViewModel (private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    fun fetchNews(languageCode: String) {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlinesByLanguage(languageCode)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}