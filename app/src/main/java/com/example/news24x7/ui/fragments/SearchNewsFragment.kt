package com.example.news24x7.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news24x7.R
import com.example.news24x7.adapter.NewsAdapter
import com.example.news24x7.ui.NewsActivity
import com.example.news24x7.ui.NewsViewModel
import com.example.news24x7.utils.Constants.Companion.SEARCH_NEWS_DELAY_TIME
import com.example.news24x7.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment: Fragment(R.layout.fragment_search_news) {

    private lateinit var paginationProgressBar: ProgressBar
    private lateinit var rvSearchNews: RecyclerView
    private lateinit var etSearch: EditText
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSearchNews = view.findViewById<RecyclerView>(R.id.rvSearchNews)
        paginationProgressBar = view.findViewById<ProgressBar>(R.id.paginationProgressBar)
        etSearch = view.findViewById(R.id.etSearch)
        viewModel = (activity as NewsActivity).viewModel
        setupRecycleView()

        var job: Job? = null
        etSearch.addTextChangedListener { text: Editable? ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_DELAY_TIME)
                text?.let {
                    if (text.toString().isNotEmpty())
                        viewModel.getSearchNews(text.toString())
                }
            }
        }

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { respose->
            when(respose){
                is Resource.Success -> {
                    hideProgressBar()
                    respose.body?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    respose.message?.let {
                        Log.e("Search News Tag", "Error Message ${respose.message}")
                    }
                }
                is Resource.Loading -> showProgressBar()
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecycleView(){
        newsAdapter = NewsAdapter()
        rvSearchNews.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}