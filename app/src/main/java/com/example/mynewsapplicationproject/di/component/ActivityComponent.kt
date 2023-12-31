package com.example.mynewsapplicationproject.di.component

import com.example.mynewsapplicationproject.di.ActivityScope
import com.example.mynewsapplicationproject.di.module.ActivityModule
import com.example.mynewsapplicationproject.ui.countries.CountriesPageActivity
import com.example.mynewsapplicationproject.ui.instantsearch.InstantSearchActivity
import com.example.mynewsapplicationproject.ui.languages.LanguagesActivity
import com.example.mynewsapplicationproject.ui.newsbycountry.NewsByCountryActivity
import com.example.mynewsapplicationproject.ui.newsbylanguage.NewsByLanguageActivity
import com.example.mynewsapplicationproject.ui.newssources.NewsSourceActivity
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

    fun inject(activity: NewsSourceActivity)

    fun inject(activity: CountriesPageActivity)

    fun inject(activity: NewsByCountryActivity)

    fun inject(activity: LanguagesActivity)

    fun inject(activity: NewsByLanguageActivity)

    fun inject(activity: InstantSearchActivity)

}