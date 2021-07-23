package com.example.datasource.news

import com.example.model.news.bo.NewBo


interface NewsRemoteDataSource {
    suspend fun getNews(type: String, date: Int, optionalShareOptions: String): List<NewBo>
}