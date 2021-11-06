package com.goldcompany.apps.koreabike.ui.navigation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.kakaodata.KakaoAddressItem
import kotlinx.coroutines.launch

class NavigationViewModel(application: Application) : AndroidViewModel(application) {
    private val kBikeRepository =  KBikeRepository.getRepository(application)

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

    suspend fun searchAddress(address: String): List<KakaoAddressItem> {
        return kBikeRepository.getKeywordAddressItem(address)
    }
}