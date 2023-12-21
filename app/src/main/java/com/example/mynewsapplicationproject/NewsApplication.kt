package com.example.mynewsapplicationproject

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.mynewsapplicationproject.di.component.ApplicationComponent
import com.example.mynewsapplicationproject.di.component.DaggerApplicationComponent
import com.example.mynewsapplicationproject.di.module.ApplicationModule

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}