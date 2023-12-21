package com.example.mynewsapplicationproject.di.component

import android.content.Context
import com.example.mynewsapplicationproject.NewsApplication
import com.example.mynewsapplicationproject.data.api.NetworkService
import com.example.mynewsapplicationproject.data.repository.TopHeadlineRepository
import com.example.mynewsapplicationproject.di.ApplicationContext
import com.example.mynewsapplicationproject.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

}