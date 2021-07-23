package com.example.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.R
import com.example.databinding.NewsFilterFragmentBinding
import com.example.di.ViewModelFactory
import com.example.model.news.bo.NewsTypeFilter
import com.example.ui.activities.NewsActivity
import com.example.ui.viewmodels.SearchFilterViewModel
import javax.inject.Inject

class NewsFilterFragment : BaseFragment() {

    companion object {
        fun newInstance() = NewsFilterFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SearchFilterViewModel>
    private val viewModel: SearchFilterViewModel by viewModels { viewModelFactory }
    private var binding: NewsFilterFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsFilterFragmentBinding.inflate(inflater, container, false)
        binding?.setupScreen()
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun NewsFilterFragmentBinding.setupScreen() {
        setupTypeFilter()
        setupDateFilter()
        setupSearchButton()
    }

    private fun NewsFilterFragmentBinding.setupSearchButton() {
        newsSearchButton.setOnClickListener {
            if (viewModel.currentTypeOption == NewsTypeFilter.MOST_SHARED.position
                && viewModel.isFacebookOptionChecked.not()
                && viewModel.isTwitterOptionChecked.not()) {
                    showDialog(
                        "Error",
                        "Select at least one share option",
                        "Ok",
                        null,
                    )
            } else {
                (activity as? NewsActivity)?.showNewsList(
                    viewModel.currentTypeOption,
                    viewModel.currentDateOption,
                    viewModel.isFacebookOptionChecked,
                    viewModel.isTwitterOptionChecked,
                )
            }
        }
    }

    private fun NewsFilterFragmentBinding.setupTypeFilter() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.news_type_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            newsTypeSpinner.adapter = adapter
        }
        newsTypeSpinner.onItemSelectedListener = getTypeSpinnerListener()
        newsDateSpinner.setSelection(viewModel.currentTypeOption)
        newsShareFacebookSourceCheckBox.isChecked = viewModel.isFacebookOptionChecked
        newsShareTwitterSourceCheckBox.isChecked = viewModel.isTwitterOptionChecked
        newsShareFacebookSourceCheckBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isFacebookOptionChecked = isChecked
        }
        newsShareTwitterSourceCheckBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.isTwitterOptionChecked = isChecked
        }
    }

    private fun NewsFilterFragmentBinding.setupDateFilter() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.news_date_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            newsDateSpinner.adapter = adapter
        }
        newsDateSpinner.onItemSelectedListener = getDateSpinnerListener()
        newsDateSpinner.setSelection(viewModel.currentDateOption)
    }

    private fun NewsFilterFragmentBinding.getTypeSpinnerListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                viewModel.currentTypeOption = pos
                newsShareOptionsGroup.visibility = if (pos == 1) VISIBLE else GONE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun getDateSpinnerListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                viewModel.currentDateOption = pos
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}