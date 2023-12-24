package com.example.mynewsapplicationproject.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mynewsapplicationproject.data.repository.NewsSourcesRepository
import com.example.mynewsapplicationproject.data.repository.TopHeadlineRepository
import com.example.mynewsapplicationproject.di.ActivityContext
import com.example.mynewsapplicationproject.ui.base.ViewModelProviderFactory
import com.example.mynewsapplicationproject.ui.newssources.NewsSourceAdapter
import com.example.mynewsapplicationproject.ui.newssources.NewsSourceViewModel
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineAdapter
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @Provides
    fun provideNewsSourcesViewModel(newsSourcesRepository: NewsSourcesRepository): NewsSourceViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsSourceViewModel::class) {
                NewsSourceViewModel(newsSourcesRepository)
            })[NewsSourceViewModel::class.java]
    }

    @Provides
    fun provideNewsSourcesAdapter() = NewsSourceAdapter(ArrayList())

}