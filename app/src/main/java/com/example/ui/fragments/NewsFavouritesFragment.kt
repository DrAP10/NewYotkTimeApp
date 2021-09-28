package com.example.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.R
import com.example.databinding.NewsDetailFragmentBinding
import com.example.di.ViewModelFactory
import com.example.ui.viewmodels.NewsListViewModel
import javax.inject.Inject


class NewsFavouritesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsListViewModel>
    private val viewModel: NewsListViewModel by viewModels { viewModelFactory }
    private var binding: NewsDetailFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsDetailFragmentBinding.inflate(inflater, container, false)
//        binding?.setupScreen()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.title = getString(R.string.app_name)
        binding = null
    }

}