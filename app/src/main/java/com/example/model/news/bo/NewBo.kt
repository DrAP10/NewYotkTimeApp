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
    var isFavourite: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewBo

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
