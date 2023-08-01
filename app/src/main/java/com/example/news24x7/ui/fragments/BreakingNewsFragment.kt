package com.example.news24x7.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.news24x7.R
import com.example.news24x7.ui.NewsActivity
import com.example.news24x7.ui.NewsViewModel

class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}