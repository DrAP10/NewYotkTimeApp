package com.example.remote.news

import com.example.datasource.news.NewsRemoteDataSource
import com.example.model.news.bo.NewBo


class NewsRemoteDataSourceImpl(
    private val newsWS: NewsWS
) : NewsRemoteDataSource {

    override suspend fun getNews(type: String, date: Int, optionalShareOptions: String): List<NewBo> {
        return newsWS.getNews(type, date, optionalShareOptions).results.map { it.toBo() }
    }

}