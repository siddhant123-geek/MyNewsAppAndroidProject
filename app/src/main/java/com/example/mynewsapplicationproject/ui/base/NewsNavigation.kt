package com.example.mynewsapplicationproject.ui.base

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynewsapplicationproject.ui.HomeScreenRoute
import com.example.mynewsapplicationproject.ui.countries.CountriesRoute
import com.example.mynewsapplicationproject.ui.instantsearch.InstantSearchRoute
import com.example.mynewsapplicationproject.ui.languages.LanguagesRoute
import com.example.mynewsapplicationproject.ui.newsbylanguage.NewsByLanguageRoute
import com.example.mynewsapplicationproject.ui.newsbycountry.NewsByCountryRoute
import com.example.mynewsapplicationproject.ui.newssources.NewsSourceRoute
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineRoute
import com.example.mynewsapplicationproject.utils.DefaultNetworkHelper
import com.example.mynewsapplicationproject.utils.NetworkHelper

open class Route(val name: String) {
    object Home: Route("home")
    object TopHeadline : Route("topheadline")
    object TopHeadlineWithPaging : Route("topheadlinewithpaging")
    object NewsSource : Route("newssource")
    object Countries : Route("countries")
    object NewsByCountry : Route("{country}/newsbycountry")
    object Language : Route("language")
    object NewsByLanguage : Route("{language}/newsbylanguage")
    object Search : Route("search")
}

@Composable
fun NewsNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val netWorkHelper = DefaultNetworkHelper(context)

    NavHost(
        navController = navController,
        startDestination = Route.Home.name
    ) {
        Log.d("###", "NewsNavHost: startDest " + Route.Home.name)
        composable(route = Route.Home.name) {
            HomeScreenRoute(onItemClick = { navigateTo(it, navController) } )
        }
        composable(route = Route.TopHeadline.name) {
            TopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }
        composable(route = Route.TopHeadlineWithPaging.name) {
            TopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }
        composable(route = Route.NewsSource.name) {
            NewsSourceRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }

        // Countries
        composable(route = Route.Countries.name) {
            CountriesRoute(onItemClick = {
                // pass the iso code of the country on clicking a country
                val routeName = "$it/newsbycountry"
                Log.d("###", "NewsNavHost: it from the previous screen" + it)
                val route = Route(routeName)
                navigateTo(route, navController)
            })
        }
        composable(route = Route.NewsByCountry.name) {
            val country = it.arguments?.getString("country")
            Log.d("###", "NewsNavHost: country " + country)
            NewsByCountryRoute(onNewsClick = { openCustomChromeTab(context, it) },
                                               country = country!!,
                                               netWorkHelper = netWorkHelper)
        }

        //Languages
        composable(route = Route.Language.name) {
            LanguagesRoute(onItemClick = {
                // pass the iso code of the country on clicking a country
                val routeName = "$it/newsbylanguage"
                Log.d("###", "NewsNavHost: it from the previous screen" + it)
                val route = Route(routeName)
                navigateTo(route, navController)
            })
        }
        composable(route = Route.NewsByLanguage.name) {
            val language = it.arguments?.getString("language")
            Log.d("###", "NewsNavHost: language " + language)
            NewsByLanguageRoute(onNewsClick = { openCustomChromeTab(context, it) },
                                language = language!!,
                                netWorkHelper = netWorkHelper
            )
        }
        composable(route = Route.Search.name) {
            InstantSearchRoute(onNewsClick = {openCustomChromeTab(context, it)})
        }
    }

}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}

@Composable
fun OpenNewsScreen(newsType: String, context: Context) {
    Log.d("###", "OpenNewsScreen: newsType in navigation " + newsType)
    when(newsType) {
        ("Top Headlines") -> {
            Log.d("###", "openNewsScreen: coming inside the top headlines newsType")
            TopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }
        else -> {
            Log.d("###", "OpenNewsScreen: coming to else in newsNav")
        }
    }
}

fun navigateTo(route: Route, navController: NavController) {
    navController.navigate(route.name)
}


