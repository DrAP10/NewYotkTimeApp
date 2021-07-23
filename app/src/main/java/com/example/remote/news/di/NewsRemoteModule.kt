package com.example.remote.news.di

import com.example.datasource.news.NewsRemoteDataSource
import com.example.remote.news.NewsRemoteDataSourceImpl
import com.example.remote.news.NewsWS
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class NewsRemoteModule {

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsWS {
        return retrofit.create(NewsWS::class.java)
    }

    @Provides
    fun newsRemoteDataSourceProvider(newsService: NewsWS) =
        NewsRemoteDataSourceImpl(newsService) as NewsRemoteDataSource

}