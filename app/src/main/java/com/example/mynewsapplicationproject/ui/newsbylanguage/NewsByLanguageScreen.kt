package com.example.mynewsapplicationproject.ui.newsbylanguage

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineScreen
import com.example.mynewsapplicationproject.utils.IsoCodes
import com.example.mynewsapplicationproject.utils.NetworkHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsByLanguageRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: NewsByLanguageViewModel = hiltViewModel(),
    language: String,
    netWorkHelper: NetworkHelper
) {
    val languageCode = IsoCodes.languageToIsoCodeMap[language]
    Log.d("###", "NewsByLanguageScreen: code in news By country " + languageCode)
    if(netWorkHelper.isNetworkConnected()) {
        viewModel.fetchNews(languageCode!!)
    }
    else {
        viewModel.fetchNewsDirectlyFromDB()
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopHeadlineScreen(uiState, onNewsClick)
        }
    })
}