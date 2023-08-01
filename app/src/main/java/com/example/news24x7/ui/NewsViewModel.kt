package com.example.news24x7.ui

import androidx.lifecycle.ViewModel
import com.example.news24x7.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
): ViewModel() {
}