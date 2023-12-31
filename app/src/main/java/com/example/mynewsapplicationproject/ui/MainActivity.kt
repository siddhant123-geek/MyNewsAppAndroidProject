package com.example.mynewsapplicationproject.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mynewsapplicationproject.data.model.ContentToSee
import com.example.mynewsapplicationproject.databinding.ActivityMainBinding
import com.example.mynewsapplicationproject.ui.countries.CountriesPageActivity
import com.example.mynewsapplicationproject.ui.instantsearch.InstantSearchActivity
import com.example.mynewsapplicationproject.ui.languages.LanguagesActivity
import com.example.mynewsapplicationproject.ui.newssources.NewsSourceActivity
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineActivity

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAppTexts: ArrayList<ContentToSee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val contentTexts = listOf(
            "Top Headlines",
            "News Sources",
            "Countries",
            "Languages",
            "Search"
        )

        newsAppTexts = ArrayList()

        for(i in contentTexts.indices) {
            val contentText = contentTexts[i]
            val contentToSee = ContentToSee(contentText)

            newsAppTexts.add(contentToSee)
        }
        binding.listView.adapter = MainActivityAdapter(this, newsAppTexts)

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                val intent = Intent(this, TopHeadlineActivity::class.java)
                startActivity(intent)
            }
            else if (position == 1) {
                val intent = Intent(this, NewsSourceActivity::class.java)
                startActivity(intent)
            }
            else if (position == 2){
                val intent = Intent(this, CountriesPageActivity::class.java)
                startActivity(intent)
            }
            else if (position == 3){
                val intent = Intent(this, LanguagesActivity::class.java)
                startActivity(intent)
            }
            else {
                val intent = Intent(this, InstantSearchActivity::class.java)
                startActivity(intent)
            }

        }
    }
}