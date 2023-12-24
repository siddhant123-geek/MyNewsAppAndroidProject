package com.example.mynewsapplicationproject.ui.newssources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapplicationproject.data.model.Source
import com.example.mynewsapplicationproject.data.repository.NewsSourcesRepository
import com.example.mynewsapplicationproject.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsSourceViewModel(private val newsSourcesRepository: NewsSourcesRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Source>>> (UiState.Loading)

    val uiState: StateFlow<UiState<List<Source>>> = _uiState

    init {
        fetchNewsSources()
    }

    private fun fetchNewsSources() {
        viewModelScope.launch {
            newsSourcesRepository.getNewsSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}