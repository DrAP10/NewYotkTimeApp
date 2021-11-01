package com.example.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.R
import com.example.databinding.NewsFavouritesFragmentBinding
import com.example.di.ViewModelFactory
import com.example.model.news.bo.NewBo
import com.example.ui.activities.NewsActivity
import com.example.ui.adapters.NewsAdapter
import com.example.ui.adapters.NewsListListener
import com.example.ui.viewmodels.NewsListViewModel
import javax.inject.Inject


class NewsFavouritesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsListViewModel>
    private val viewModel: NewsListViewModel by viewModels { viewModelFactory }
    private var binding: NewsFavouritesFragmentBinding? = null
    private lateinit var mNewsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsFavouritesFragmentBinding.inflate(inflater, container, false)
        binding?.let {
            it.setupScreen()
            configureObservers()
            viewModel.getFavouritesNews()
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.title = getString(R.string.app_name)
        binding = null
    }

    fun NewsFavouritesFragmentBinding.setupScreen() {
        mNewsAdapter = NewsAdapter(getNewsListListener())

        favouriteRecyclerview.apply {
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

            override fun onFavClicked(news: NewBo) {
                if (news.isFavourite) {
                    viewModel.removeFavouriteNew(news)
                } else {
                    viewModel.addFavouriteNew(news)
                }
                viewModel.getFavouritesNews()
            }
        }
    }

    private fun configureObservers() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer { result ->
            mNewsAdapter.updateData(result)
            println("ob fav")
        })
    }

}