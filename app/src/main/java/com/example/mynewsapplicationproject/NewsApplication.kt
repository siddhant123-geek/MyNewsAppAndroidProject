package com.example.mynewsapplicationproject

import android.app.Application
//import com.example.mynewsapplicationproject.di.component.ApplicationComponent
//import com.example.mynewsapplicationproject.di.component.DaggerApplicationComponent
//import com.example.mynewsapplicationproject.di.module.ApplicationModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application()