package com.example.remote.news.dto

data class NewsResponseDto(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: List<NewDto>,
)
