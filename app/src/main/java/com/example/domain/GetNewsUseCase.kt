package com.example.domain

import com.example.model.news.bo.NewBo
import com.example.repository.news.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface GetNewsUseCase {

    suspend operator fun invoke(type: String, date: Int, optionalShareOptions: String = ""): Flow<List<NewBo>>

}

class GetNewsUseCaseImpl(
    private val repository: NewsRepository
) : GetNewsUseCase {

    override suspend operator fun invoke(type: String, date: Int, optionalShareOptions: String): Flow<List<NewBo>> = flow {
        repository.getNews(type, date, optionalShareOptions)
    }

}