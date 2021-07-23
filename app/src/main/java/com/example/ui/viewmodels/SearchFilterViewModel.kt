package com.example.ui.viewmodels

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SearchFilterViewModel @Inject constructor(): ViewModel() {

    var currentTypeOption = 0
    var currentDateOption = 0
    var isFacebookOptionChecked = false
    var isTwitterOptionChecked = false

}