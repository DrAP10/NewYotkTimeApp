package com.example.local.news

import com.example.datasource.news.NewsLocalDataSource
import com.example.model.news.bo.NewBo


class NewsLocalDataSourceImpl(
    private val newsDao: NewsDao
) : NewsLocalDataSource {

    override suspend fun getFavouritesNews(): List<NewBo> =
        newsDao.getFavouriteNews().map { it.toBo() }


    override suspend fun insertFavouritesNews(new: NewBo) {
        newsDao.insert(new.toDbo())
    }

    override suspend fun removeFavouritesNews(new: NewBo) {
        newsDao.remove(new.id)
    }


}