package com.example.news24x7

import com.example.news24x7.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)