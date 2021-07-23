package com.example.ui.fragments

import android.app.ProgressDialog
import android.content.DialogInterface
import android.icu.text.CaseMap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.R
import com.example.databinding.NewsDetailFragmentBinding


class NewsDetailFragment : BaseFragment() {

    private var binding: NewsDetailFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsDetailFragmentBinding.inflate(inflater, container, false)
        binding?.setupScreen(
            arguments?.getString(NEWS_URL_KEY),
            arguments?.getString(NEWS_TITLE_KEY),
        )
        return binding?.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        activity?.title = getString(R.string.app_name)
        binding = null
    }

    private fun NewsDetailFragmentBinding.setupScreen(url: String?, title: String?) {
        activity?.title = title ?: getString(R.string.app_name)
        if (url?.isNotEmpty() == true) {
            newsDetailWebView.loadUrl(url)
            newsDetailWebView.webViewClient = object : WebViewClient() {

                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError) {
                    if (error.description != null && error.description != "net::ERR_BLOCKED_BY_RESPONSE") {
                            showDialog(
                                "Error",
                                "The web page could not be loaded, please try again later",
                                "Ok",
                                getErrorDialogListener()
                            )
                    }

                }
            }
        } else {
            showDialog(
                "Error",
                "URL not valid",
                "Ok",
                getErrorDialogListener()
            )
        }
    }

    private fun getErrorDialogListener(): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()
            activity?.onBackPressed()
        }
    }

    companion object {

        private const val NEWS_URL_KEY = "newsUrl"
        private const val NEWS_TITLE_KEY = "newsTitle"

        fun newInstance(
            newsUrl: String?,
            newsTitle: String?,
        ): NewsDetailFragment {
            val fragment = NewsDetailFragment()

            val args = Bundle()
            args.putString(NEWS_URL_KEY, newsUrl)
            args.putString(NEWS_TITLE_KEY, newsTitle)
            fragment.arguments = args

            return fragment
        }
    }
}