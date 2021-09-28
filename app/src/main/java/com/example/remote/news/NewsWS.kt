package com.example.remote.news

import com.example.BuildConfig
import com.example.remote.news.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NewsWS {
    @GET("mostpopular/v2/{type}/{optionalShareOptions}/{date}.json")
    suspend fun getNews(
        @Path("type") type: String,
        @Path("date") date: Int,
        @Path("optionalShareOptions") optionalShareOptions: String,
        @Query("api-key") apiKey: String = BuildConfig.NEWS_API_KEY,
    ): NewsResponseDto

    @GET("search/v2/articlesearch.json")
    suspend fun searchNews(
        @Query("q") search: String,
        @Query("api-key") apiKey: String = BuildConfig.NEWS_API_KEY,
    ): NewsResponseDto
}