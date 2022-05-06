package com.goldcompany.apps.koreabike.ui.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.Result
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel@Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {
    val isStart: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val startCoordinate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val startAddressName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val endCoordinate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val endAddressName: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private var currentQuery: String? = null
    private var currentSearchResult: Flow<PagingData<AddressItem>>? = null

    fun searchAddress(address: String): Flow<PagingData<AddressItem>> {
        val lastResult = currentSearchResult
        if (address == currentQuery && lastResult != null) {
            return lastResult
        }
        currentQuery = address
        val newResult: Flow<PagingData<AddressItem>> = kBikeRepository.getSearchAddressStream(address)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    suspend fun getNavigationPath(): Flow<Result<ResultPath>> = flow {
        val start = startCoordinate.value.toString()
        val end = endCoordinate.value.toString()
        emit(kBikeRepository.getNavigationPath(start, end))
    }

    fun isAddressNullOrSame(): Boolean {
        val startCoordinate = startCoordinate.value
        val endCoordinate = endCoordinate.value

        if (startCoordinate.isNullOrEmpty() || endCoordinate.isNullOrEmpty()) {
            return false
        } else if(startCoordinate == endCoordinate) {
            return false
        }
        return true
    }
}