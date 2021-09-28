package com.example.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.databinding.NewsFilterFragmentBinding
import com.example.ui.adapters.MainViewPagerAdapter

class NewsMainFragment : BaseFragment() {

    companion object {
        fun newInstance() = NewsMainFragment()
    }

    private var binding: NewsFilterFragmentBinding? = null

    private lateinit var viewPagerAdapter: MainViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsFilterFragmentBinding.inflate(inflater, container, false)
        binding?.setupPager()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun NewsFilterFragmentBinding.setupPager() {
        viewPagerAdapter = MainViewPagerAdapter(childFragmentManager)
        pager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(pager)
    }

}