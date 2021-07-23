package com.example.remote.news

import com.example.remote.news.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path


interface NewsWS {
    @GET("{type}/{optionalShareOptions}/{date}.json?api-key=2bB0BGGe2pysadVQorASzqyClBzI5w1G")
    suspend fun getNews(
        @Path("type") type: String,
        @Path("date") date: Int,
        @Path("optionalShareOptions") optionalShareOptions: String,
    ): NewsResponseDto
}