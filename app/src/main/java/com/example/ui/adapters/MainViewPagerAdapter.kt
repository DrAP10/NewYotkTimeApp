package com.example.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.ui.fragments.NewsFavouritesFragment
import com.example.ui.fragments.NewsListFragment


class MainViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int  = 2

    override fun getItem(index: Int): Fragment {
        return if (index == 0) NewsListFragment() else NewsFavouritesFragment()
    }

    override fun getPageTitle(index: Int): CharSequence {
        return if (index == 0) "News" else "Favourites"
    }
}
