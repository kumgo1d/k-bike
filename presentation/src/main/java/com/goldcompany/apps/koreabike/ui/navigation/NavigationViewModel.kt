package com.goldcompany.apps.koreabike.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goldcompany.apps.koreabike.R
import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.model.navigation.Navigation
import com.goldcompany.koreabike.domain.usecase.GetNavigationPathUseCase
import com.goldcompany.koreabike.domain.usecase.SearchAddressUseCase
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
    private val searchAddressUseCase: SearchAddressUseCase,
    private val getNavigationPathUseCase: GetNavigationPathUseCase
) : ViewModel() {
    val isStart: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    private val _startAddress = MutableLiveData<NavAddress>()
    val startAddress: LiveData<NavAddress> = _startAddress

    private val _endAddress = MutableLiveData<NavAddress>()
    val endAddress: LiveData<NavAddress> = _endAddress

    private val _resultMessage = MutableLiveData<Int>()
    val resultMessage: LiveData<Int> = _resultMessage

    fun setStartNavAddress(address: NavAddress) {
        _startAddress.postValue(address)
    }

    fun setEndNavAddress(address: NavAddress) {
        _endAddress.postValue(address)
    }

    suspend fun searchAddress(address: String, page: Int): Flow<List<Address>> = flow {
        emit(searchAddressUseCase(address, page))
    }

    suspend fun getNavigationPath(start: String, end: String): Flow<Navigation> = flow {
        emit(getNavigationPathUseCase(start, end))
    }

    fun isAddressCorrect(): Boolean {
        val startCoordinate = startAddress.value?.coordinate ?: ""
        val endCoordinate = endAddress.value?.coordinate ?: ""

        if (startCoordinate.isEmpty() || endCoordinate.isEmpty()) {
            _resultMessage.postValue(R.string.error_code)
            return false
        } else if (startCoordinate == endCoordinate) {
            _resultMessage.postValue(R.string.error_code)
            return false
        }
        return true
    }
}