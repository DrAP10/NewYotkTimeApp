package com.example.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.news.bo.NewBo
import com.example.model.news.bo.NewsDateFilter
import com.example.model.news.bo.NewsTypeFilter
import com.example.repository.news.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val newsLiveData: MutableLiveData<List<NewBo>> = MutableLiveData()

    fun getNews(
        typeFilter: NewsTypeFilter,
        dateFilter: NewsDateFilter,
        isFacebookChecked: Boolean,
        isTwitterChecked: Boolean,
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {

            val shareOptions = getShareOptions(typeFilter, isFacebookChecked, isTwitterChecked)

            newsLiveData.postValue(
                newsRepository.getNews(
                    typeFilter.getTypeSearchString(),
                    dateFilter.getDateSearchInt(),
                    shareOptions
                )
            )
        }
    }

    fun getFavouritesNews(
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {

            newsLiveData.postValue(
                newsRepository.getFavouriteNews()
            )
        }
    }

    fun addFavouriteNew(new: NewBo) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            newsRepository.addFavouriteNew(new)
        }
    }

    fun removeFavouriteNew(new: NewBo) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            newsRepository.removeFavouriteNew(new)
        }
    }

    private fun getShareOptions(
        typeFilter: NewsTypeFilter,
        isFacebookChecked: Boolean,
        isTwitterChecked: Boolean
    ): String {
        return if (typeFilter == NewsTypeFilter.MOST_SHARED && isFacebookChecked && isTwitterChecked) {
            "all-sections/facebook;twitter"
        } else if (typeFilter == NewsTypeFilter.MOST_SHARED && isFacebookChecked) {
            "all-sections/facebook"
        } else if (typeFilter == NewsTypeFilter.MOST_SHARED && isTwitterChecked) {
            "all-sections/twitter"
        } else {
            "all-sections"
        }
    }
}