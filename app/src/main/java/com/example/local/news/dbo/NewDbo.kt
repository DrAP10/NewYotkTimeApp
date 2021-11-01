package com.example.local.news.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class NewDbo(
    @PrimaryKey val id: Long,
    val title: String?,
    val author: String?,
    val section: String?,
    val publishedDate: Date?,
    val image: String?,
    val url: String?,
    val isFavourite: Boolean = false,
)