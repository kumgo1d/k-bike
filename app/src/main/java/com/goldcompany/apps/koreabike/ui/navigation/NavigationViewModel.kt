package com.goldcompany.apps.koreabike.ui.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.Result
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.search_address.Addresses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel@Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {
    val startCoordinate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val startAddressName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val endCoordinate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val endAddressName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    suspend fun searchAddress(address: String): Flow<Result<Addresses>> = flow {
        emit(kBikeRepository.searchAddress(address))
    }

    suspend fun getNavigationPath(): Flow<Result<ResultPath>> = flow {
        val start = startCoordinate.value.toString()
        val end = endCoordinate.value.toString()
        emit(kBikeRepository.getNavigationPath(start, end))
    }

    fun isAddressNullOrSame(): Boolean {
        val startCoordinate = startCoordinate.value
        val endCoordinate = endCoordinate.value

        if(startCoordinate.isNullOrEmpty() || endCoordinate.isNullOrEmpty()) {
            return false
        } else if(startCoordinate == endCoordinate) {
            return false
        }
        return true
    }
}