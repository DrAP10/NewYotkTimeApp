package com.example.remote.news.dto

import com.google.gson.annotations.SerializedName

data class MediaDataDto(
    val type: String?,
    val subtype: String?,
    @SerializedName("media-metadata") val mediaMetadata: List<MediaMetadataDto>?,
)

data class MediaMetadataDto(
    val url: String?,
    val height: Int?,
    val width: Int?,
)
