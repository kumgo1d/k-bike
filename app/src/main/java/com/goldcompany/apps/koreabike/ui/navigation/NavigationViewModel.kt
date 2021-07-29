package com.goldcompany.apps.koreabike.ui.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class NavigationViewModel : ViewModel() {
    val startAddress : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    var startX = ""
    var startY = ""

    val endAddress : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    var endX = ""
    var endY = ""
}