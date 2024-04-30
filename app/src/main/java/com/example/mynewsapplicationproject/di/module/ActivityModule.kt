package com.example.mynewsapplicationproject.di.module

import com.example.mynewsapplicationproject.ui.countries.CountriesPageAdapter
import com.example.mynewsapplicationproject.ui.instantsearch.InstantSearchAdapter
import com.example.mynewsapplicationproject.ui.languages.LanguagesAdapter
import com.example.mynewsapplicationproject.ui.newssources.NewsSourceAdapter
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @ActivityScoped
    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideInstantSearchAdapter() = InstantSearchAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideNewsSourcesAdapter() = NewsSourceAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideCountriesPageAdapter() = CountriesPageAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideLanguagesPageAdapter() = LanguagesAdapter(ArrayList())

}