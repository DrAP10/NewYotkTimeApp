package com.example.ui.fragments

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    fun showDialog(
        title: String,
        text: String,
        buttonText: String,
        buttonListener: DialogInterface.OnClickListener?,
    ) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(text)
            .setPositiveButton(buttonText, buttonListener)
            .setCancelable(false)
            .show()
    }

}