package com.goldcompany.apps.koreabike.ui.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    suspend fun searchAddress(address: String): Flow<Addresses> = flow {
        emit(kBikeRepository.searchAddress(address))
    }
}