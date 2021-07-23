package com.example.repository.news

import com.example.datasource.news.NewsRemoteDataSource
import com.example.model.news.bo.NewBo

interface NewsRepository {
    suspend fun getNews(type: String, date: Int, optionalShareOptions: String): List<NewBo>
}

internal class NewsRepositoryImpl(
    private val remote: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getNews(type: String, date: Int, optionalShareOptions: String): List<NewBo>
            = remote.getNews(type, date, optionalShareOptions)

}