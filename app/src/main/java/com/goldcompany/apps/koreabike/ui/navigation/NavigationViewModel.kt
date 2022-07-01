package com.goldcompany.apps.koreabike.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.goldcompany.apps.koreabike.Constants
import com.goldcompany.apps.koreabike.data.KBikeRepository
import com.goldcompany.apps.koreabike.data.driving.ResultPath
import com.goldcompany.apps.koreabike.data.search_address.AddressItem
import com.goldcompany.apps.koreabike.util.Resource
import com.goldcompany.apps.koreabike.util.Result
import com.goldcompany.apps.koreabike.util.Status
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

    private val _resultMessage = MutableLiveData<String>()
    val resultMessage: LiveData<String> = _resultMessage

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

    suspend fun getNavigationPath(start: String, end: String): Flow<ResultPath> = flow {
        val result = kBikeRepository.getNavigationPath(start, end)

        when (result.status) {
            Status.SUCCESS -> result.data?.let { emit(it) }
            Status.ERROR -> {
                _resultMessage.postValue(Constants.RESULT_ERROR)
                emit(
                    ResultPath(
                        code = 9999,
                        currentDateTime = "0",
                        message = "Error",
                        route = null
                    )
                )
            }
            Status.LOADING -> {

            }
        }
    }

    fun isAddressCorrect(): Boolean {
        val startCoordinate = startAddress.value?.coordinate ?: ""
        val endCoordinate = endAddress.value?.coordinate ?: ""

        if (startCoordinate.isEmpty() || endCoordinate.isEmpty()) {
            _resultMessage.postValue(Constants.RESULT_ERROR)
            return false
        } else if (startCoordinate == endCoordinate) {
            _resultMessage.postValue(Constants.RESULT_ERROR)
            return false
        }
        return true
    }
}