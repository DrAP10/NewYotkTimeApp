package com.example.repository.news

import com.example.datasource.news.NewsLocalDataSource
import com.example.datasource.news.NewsRemoteDataSource
import com.example.model.news.bo.NewBo

interface NewsRepository {
    suspend fun getNews(type: String, date: Int, optionalShareOptions: String): List<NewBo>
    suspend fun getFavouriteNews(): List<NewBo>
    suspend fun addFavouriteNew(new: NewBo)
    suspend fun removeFavouriteNew(new: NewBo)
}

internal class NewsRepositoryImpl(
    private val remote: NewsRemoteDataSource,
    private val local: NewsLocalDataSource,
) : NewsRepository {

    override suspend fun getNews(type: String, date: Int, optionalShareOptions: String): List<NewBo> {
        val news: List<NewBo> = remote.getNews(type, date, optionalShareOptions)
        val favouriteNews = local.getFavouritesNews()
        for (new in news) {
            if (favouriteNews.contains(new)) {
                new.isFavourite = true
            }
        }
        return news
    }

    override suspend fun getFavouriteNews(): List<NewBo> =
        local.getFavouritesNews()

    override suspend fun addFavouriteNew(new: NewBo) =
        local.insertFavouritesNews(new)

    override suspend fun removeFavouriteNew(new: NewBo) =
        local.removeFavouritesNews(new)

}