package com.example.ui.di

import com.example.ui.fragments.NewsFavouritesFragment
import com.example.ui.fragments.NewsMainFragment
import com.example.ui.fragments.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindNewsFilterFragment(): NewsMainFragment

    @ContributesAndroidInjector
    internal abstract fun bindNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    internal abstract fun bindNewsDetailFragment(): NewsFavouritesFragment
}
