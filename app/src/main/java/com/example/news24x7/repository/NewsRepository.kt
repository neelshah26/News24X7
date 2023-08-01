package com.example.news24x7.repository

import com.example.news24x7.api.RetrofitInstance
import com.example.news24x7.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNum: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNum)

}