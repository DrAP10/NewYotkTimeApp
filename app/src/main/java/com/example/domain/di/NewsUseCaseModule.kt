package com.example.domain.di

import com.example.domain.GetNewsUseCase
import com.example.domain.GetNewsUseCaseImpl
import com.example.repository.news.NewsRepository
import dagger.Module
import dagger.Provides


@Module
class NewsUseCaseModule {

    @Provides
    fun getNewsUseCaseProvider(newsRepository: NewsRepository) =
        GetNewsUseCaseImpl(newsRepository) as GetNewsUseCase

}