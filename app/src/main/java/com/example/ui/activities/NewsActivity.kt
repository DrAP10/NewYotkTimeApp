package com.example.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.R
import com.example.ui.fragments.NewsDetailFragment
import com.example.ui.fragments.NewsListFragment
import com.example.ui.fragments.NewsFilterFragment


class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        inflateFragment(NewsFilterFragment.newInstance(), false)
    }

    fun showNewsList(
        typeFilterPosition: Int,
        dateFilterPosition: Int,
        isFacebookChecked: Boolean = false,
        isTwitterChecked: Boolean = false,
    ) {
        inflateFragment(
            NewsListFragment.newInstance(
                typeFilterPosition,
                dateFilterPosition,
                isFacebookChecked,
                isTwitterChecked,
            ), true
        )
    }

    fun showDetail(url: String?, title: String?) {
        inflateFragment(NewsDetailFragment.newInstance(url, title), true)
    }

    private fun inflateFragment(fragment: Fragment, addToBackStack: Boolean) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        if (addToBackStack) {
            ft.addToBackStack(null)
        }
        ft.commitAllowingStateLoss()
    }
}