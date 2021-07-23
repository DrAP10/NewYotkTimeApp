package com.example.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databinding.NewsListFragmentBinding
import com.example.di.ViewModelFactory
import com.example.model.news.bo.NewBo
import com.example.model.news.bo.NewsDateFilter
import com.example.model.news.bo.NewsTypeFilter
import com.example.ui.activities.NewsActivity
import com.example.ui.adapters.NewsAdapter
import com.example.ui.adapters.NewsListListener
import com.example.ui.viewmodels.NewsListViewModel
import javax.inject.Inject

class NewsListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsListViewModel>
    private val viewModel: NewsListViewModel by viewModels { viewModelFactory }
    private var binding: NewsListFragmentBinding? = null
    private lateinit var mNewsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsListFragmentBinding.inflate(inflater, container, false)
        binding?.let {
            it.setupScreen()
            configureObservers()
            viewModel.getNews(
                NewsTypeFilter.fromId(arguments?.getInt(TYPE_FILTER_KEY, DEFAULT_TYPE_FILTER)),
                NewsDateFilter.fromId(arguments?.getInt(DATE_FILTER_KEY, DEFAULT_DATE_FILTER)),
                arguments?.getBoolean(IS_FACEBOOK_CHECKED_FILTER_KEY) ?: DEFAULT_SHARE_OPTION_FILTER,
                arguments?.getBoolean(IS_TWITTER_CHECKED_FILTER_KEY) ?: DEFAULT_SHARE_OPTION_FILTER,
            )
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun configureObservers() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer { result ->
            mNewsAdapter.updateData(result)
        })
    }

    private fun NewsListFragmentBinding.setupScreen() {
        mNewsAdapter = NewsAdapter(getNewsListListener())

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mNewsAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun getNewsListListener(): NewsListListener {
        return object : NewsListListener {
            override fun onNewsSelected(news: NewBo) {
                (activity as? NewsActivity)?.showDetail(news.url, news.title)
            }
        }
    }

    companion object {

        private const val TYPE_FILTER_KEY = "typeFilterPosition"
        private const val DATE_FILTER_KEY = "dateFilterPosition"
        private const val IS_FACEBOOK_CHECKED_FILTER_KEY = "isFacebookChecked"
        private const val IS_TWITTER_CHECKED_FILTER_KEY = "isTwitterChecked"
        private const val DEFAULT_TYPE_FILTER = 0
        private const val DEFAULT_DATE_FILTER = 0
        private const val DEFAULT_SHARE_OPTION_FILTER = false

        fun newInstance(
            typeFilterPosition: Int,
            dateFilterPosition: Int,
            isFacebookChecked: Boolean = false,
            isTwitterChecked: Boolean = false,
        ): NewsListFragment {
            val fragment = NewsListFragment()

            val args = Bundle()
            args.putInt(TYPE_FILTER_KEY, typeFilterPosition)
            args.putInt(DATE_FILTER_KEY, dateFilterPosition)
            args.putBoolean(IS_FACEBOOK_CHECKED_FILTER_KEY, isFacebookChecked)
            args.putBoolean(IS_TWITTER_CHECKED_FILTER_KEY, isTwitterChecked)
            fragment.arguments = args

            return fragment
        }
    }
}