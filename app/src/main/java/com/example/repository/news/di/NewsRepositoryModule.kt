package com.example.repository.news.di

import com.example.datasource.news.NewsRemoteDataSource
import com.example.repository.news.NewsRepository
import com.example.repository.news.NewsRepositoryImpl
import dagger.Module
import dagger.Provides


@Module
class NewsRepositoryModule {

    @Provides
    fun newsRepositoryProvider(remote: NewsRemoteDataSource) =
        NewsRepositoryImpl(remote) as NewsRepository

}