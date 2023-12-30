package com.example.mynewsapplicationproject.ui.instantsearch

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapplicationproject.NewsApplication
import com.example.mynewsapplicationproject.data.model.Article
import com.example.mynewsapplicationproject.databinding.ActivityInstantSearchBinding
import com.example.mynewsapplicationproject.di.component.DaggerActivityComponent
import com.example.mynewsapplicationproject.di.module.ActivityModule
import com.example.mynewsapplicationproject.ui.base.UiState
import com.example.mynewsapplicationproject.ui.topheadline.TopHeadlineAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class InstantSearchActivity: AppCompatActivity() {

    @Inject
    lateinit var instantSearchViewModel: InstantSearchViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivityInstantSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        binding = ActivityInstantSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                instantSearchViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.instantSearchProgressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.instantSearchRecyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.instantSearchProgressBar.visibility = View.VISIBLE
                            binding.instantSearchRecyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            binding.instantSearchProgressBar.visibility = View.GONE
                            binding.instantSearchRecyclerView.visibility = View.GONE
                            Toast.makeText(this@InstantSearchActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun setupUI() {
        val recyclerView = binding.instantSearchRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                instantSearchViewModel.searchNews(newText)
                return true
            }
        })
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