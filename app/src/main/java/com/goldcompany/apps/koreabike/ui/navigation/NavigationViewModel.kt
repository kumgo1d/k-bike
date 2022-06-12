package com.goldcompany.apps.koreabike.ui.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.util.Result
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class NavAddress(
    val addressName: String,
    val coordinate: String
)

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val kBikeRepository: KBikeRepository
) : ViewModel() {
    val isStart: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val startAddress: MutableLiveData<NavAddress> by lazy { MutableLiveData<NavAddress>() }
    val endAddress: MutableLiveData<NavAddress> by lazy { MutableLiveData<NavAddress>() }

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

    suspend fun getNavigationPath(): Flow<ResultPath> = flow {
        val start = startAddress.value?.coordinate ?: ""
        val end = endAddress.value?.coordinate ?: ""

        val result = kBikeRepository.getNavigationPath(start, end)
        if (result is Result.Success) {
            emit(result.data)
        } else {
            emit(
                ResultPath(
                    code = 9999,
                    currentDateTime = "0",
                    message = "Error",
                    route = null
                )
            )
        }
    }

    fun isAddressNullOrSame(): Boolean {
        val startCoordinate = startAddress.value?.coordinate ?: ""
        val endCoordinate = endAddress.value?.coordinate ?: ""

        if (startCoordinate.isEmpty() || endCoordinate.isEmpty()) {
            return false
        } else if (startCoordinate == endCoordinate) {
            return false
        }
        return true
    }
}