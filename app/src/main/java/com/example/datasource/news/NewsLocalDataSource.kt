package com.example.datasource.news

import com.example.model.news.bo.NewBo


interface NewsLocalDataSource {
    suspend fun getFavouritesNews(): List<NewBo>
    suspend fun insertFavouritesNews(new: NewBo)
    suspend fun removeFavouritesNews(new: NewBo)
}