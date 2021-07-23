package com.example.remote.news.dto

import com.google.gson.annotations.SerializedName

data class NewDto(
    val id: Long,
    val title: String?,
    @SerializedName("byline") val author: String?,
    val section: String?,
    @SerializedName("published_date") val publishedDate: String?,
    val media: List<MediaDataDto>?,
    val url: String?,
) {

    fun getFirstImageOrNull(): String? {
        media?.let {
            for (item in it) {
                if (item.type == "image" && item.subtype == "photo") {
                    return item.mediaMetadata?.first()?.url
                }
            }

        }
        return null
    }
}