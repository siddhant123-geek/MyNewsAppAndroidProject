package com.example.mynewsapplicationproject.ui.instantsearch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mynewsapplicationproject.R
import com.example.mynewsapplicationproject.data.local.entity.Article
import com.example.mynewsapplicationproject.data.model.ApiArticle
import com.example.mynewsapplicationproject.ui.base.ShowError
import com.example.mynewsapplicationproject.ui.base.ShowLoading
import com.example.mynewsapplicationproject.ui.base.UiState
import com.example.mynewsapplicationproject.ui.topheadline.ArticleList

@Composable
fun InstantSearchRoute(
    onNewsClick: (String) -> Unit,
    viewModel: InstantSearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()

    SearchScreen(
        uiState,
        query,
        onNewsClick
    ) {
        viewModel.searchNews(it)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    uiState: UiState<List<Article>>,
    query: String,
    onNewsClick: (url: String) -> Unit,
    onSearchQueryChange: (String) -> Unit
) {
    SearchBar(
        query = query,
        onQueryChange = onSearchQueryChange,
        onSearch = {},
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        active = true,
        onActiveChange = {},
        tonalElevation = 0.dp
    ) {
        when (uiState) {
            is UiState.Success -> {
                if (uiState.data.isEmpty()) {
                    ShowError(text = "No news for now")
                }
                ArticleList(uiState.data, onNewsClick)
            }

            is UiState.Loading -> {
                ShowLoading()
            }

            is UiState.Error -> {
                ShowError(uiState.message)
            }
        }
    }
}


