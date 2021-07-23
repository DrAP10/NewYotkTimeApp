package com.example.model.news.bo

import java.util.*


data class NewBo(
    val id: Long,
    val title: String?,
    val author: String?,
    val section: String?,
    val publishedDate: Date?,
    val image: String?,
    val url: String?,
)
