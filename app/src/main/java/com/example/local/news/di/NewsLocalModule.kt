package com.example.local.news.di

import com.example.datasource.news.NewsLocalDataSource
import com.example.local.news.NewsDao
import com.example.local.news.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides


@Module
class NewsLocalModule {

    @Provides
    fun newsLocalDataSourceProvider(newsDao: NewsDao) =
        NewsLocalDataSourceImpl(newsDao) as NewsLocalDataSource

}