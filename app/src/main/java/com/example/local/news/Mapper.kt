package com.example.local.news

import com.example.local.news.dbo.NewDbo
import com.example.model.news.bo.NewBo


fun NewDbo.toBo() = NewBo(
    id,
    title,
    author,
    section,
    publishedDate,
    image,
    url
)

fun NewBo.toDbo() = NewDbo(
    id,
    title,
    author,
    section,
    publishedDate,
    image,
    url
)