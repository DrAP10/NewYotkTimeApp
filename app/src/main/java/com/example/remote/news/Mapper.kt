package com.example.remote.news

import com.example.DateUtils
import com.example.model.news.bo.NewBo
import com.example.remote.news.dto.NewDto


fun NewDto.toBo() = NewBo(
    id,
    title,
    author,
    section,
    publishedDate?.let { DateUtils.getNewsDateFormatted(it) },
    getFirstImageOrNull(),
    url
)