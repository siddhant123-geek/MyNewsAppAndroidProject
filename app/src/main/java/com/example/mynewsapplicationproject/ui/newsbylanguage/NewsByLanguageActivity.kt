package com.example.mynewsapplicationproject.ui.newsbylanguage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapplicationproject.NewsApplication
import com.example.mynewsapplicationproject.data.model.Article
import com.example.mynewsapplicationproject.databinding.ActivityTopHeadlineBinding
import com.example.mynewsapplicationproject.di.component.DaggerActivityComponent
import com.example.mynewsapplicationproject.di.module.ActivityModule
import com.example.mynewsapplicationproject.ui.base.UiState
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineAdapter
import com.example.mynewsapplicationproject.utils.AppConstant
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsByLanguageActivity: AppCompatActivity() {

    @Inject
    lateinit var newsByLanguageViewModel: NewsByLanguageViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivityTopHeadlineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityTopHeadlineBinding.inflate(layoutInflater)
        val languageCode: String? = intent.getStringExtra(AppConstant.ISO_CODE_KEY)
        setContentView(binding.root)
        setupUI()
        Log.d("###", "onCreate: languageCode inside the newsBy Language Activity $languageCode")
        setupObserver(languageCode!!)
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver(languageCode: String) {
        newsByLanguageViewModel.fetchNews(languageCode)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsByLanguageViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@NewsByLanguageActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}