package com.example.mynewsapplicationproject.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mynewsapplicationproject.data.model.ContentToSee
import com.example.mynewsapplicationproject.databinding.ActivityMainBinding
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
            val intent = Intent(this, TopHeadlineActivity::class.java)
            startActivity(intent)
        }
    }
}