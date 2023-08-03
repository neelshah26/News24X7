package com.example.news24x7.repository

import com.example.news24x7.api.RetrofitInstance
import com.example.news24x7.db.ArticleDatabase
import com.example.news24x7.models.Article

class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNum: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNum)

    suspend fun getSearchNews(queryWord: String, pageNum: Int) =
        RetrofitInstance.api.searchForNews(queryWord,pageNum)

    suspend fun saveArticle(article: Article) = db.getArticleDao().insert(article)

    fun getSavedArticle() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}