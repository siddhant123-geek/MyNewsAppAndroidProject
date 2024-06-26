package com.example.mynewsapplicationproject.ui.newssources

import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mynewsapplicationproject.data.local.entity.Source
import com.example.mynewsapplicationproject.data.model.ApiSource
import com.example.mynewsapplicationproject.ui.base.CreateHeading
import com.example.mynewsapplicationproject.ui.base.ShowError
import com.example.mynewsapplicationproject.ui.base.ShowLoading
import com.example.mynewsapplicationproject.ui.base.UiState
import com.example.mynewsapplicationproject.utils.AppConstant.SOURCES
import org.tinylog.Logger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSourceRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: NewsSourceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NewsSourceScreen(uiState, onNewsClick)
        }
    })

}

@Composable
fun NewsSourceScreen(uiState: UiState<List<Source>>, onNewsClick: (url: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            Logger.debug("coming inside the success inside news sources Ui state", null)
            SourceHeading()
            SourceList(uiState.data, onNewsClick)
        }

        is UiState.Error -> {
            Logger.error("Coming inside error in news sources with error message as - ${uiState.message}", null)
            ShowError(uiState.message)
        }

        else -> {
            Logger.debug("Coming inside loading in news sources", null)
            ShowLoading()
        }
    }
}

@Composable
fun SourceHeading() {
    CreateHeading(nameOfTheScreen = SOURCES)
}


@Composable
fun SourceList(sources: List<Source>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(sources, key = { source -> source.id }) { source ->
            SourceComposable(source, onNewsClick)
        }
    }
}

@Composable
fun SourceComposable(source: Source, onNewsClick: (url: String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            ) // Add border
            .clickable {
                if (!source.url.isNullOrEmpty()) {
                    onNewsClick(source.url)
                }
            }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Cyan)
        ) {
            TitleText(source.name)
            Spacer(modifier = Modifier.height(8.dp))
            DescriptionText(source.name)
        }
    }
}

@Composable
fun TitleText(title: String) {
    if (title.isNotEmpty()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun DescriptionText(description: String?) {
    if (!description.isNullOrEmpty()) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}
