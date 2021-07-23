package com.example.ui.di

import com.example.ui.fragments.NewsDetailFragment
import com.example.ui.fragments.NewsFilterFragment
import com.example.ui.fragments.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindNewsFilterFragment(): NewsFilterFragment

    @ContributesAndroidInjector
    internal abstract fun bindNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    internal abstract fun bindNewsDetailFragment(): NewsDetailFragment
}
