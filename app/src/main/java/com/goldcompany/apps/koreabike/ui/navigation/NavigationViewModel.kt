package com.goldcompany.apps.koreabike.ui.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val startAddress : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    var startX = "0.0"
    var startY = "0.0"

    val endAddress : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    var endX = "0.0"
    var endY = "0.0"
}