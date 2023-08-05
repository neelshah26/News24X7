package com.example.news24x7.models

import com.example.news24x7.models.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)