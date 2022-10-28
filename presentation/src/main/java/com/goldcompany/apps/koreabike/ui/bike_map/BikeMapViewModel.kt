package com.goldcompany.apps.koreabike.ui.bike_map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.util.LoadingState
import com.goldcompany.koreabike.domain.model.Result
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.usecase.GetCurrentAddressUseCase
import com.goldcompany.koreabike.domain.usecase.SearchNearbyPlacesForMarkerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BikeMapUiState(
    val isLoading: LoadingState = LoadingState.INIT,
    val address: Address? = null,
    val markerCode: String = "",
    val markerList: List<Address> = emptyList()
)

@HiltViewModel
class BikeMapViewModel @Inject constructor(
    private val searchNearbyPlacesForMarkerUseCase: SearchNearbyPlacesForMarkerUseCase,
    private val getCurrentAddressUseCase: GetCurrentAddressUseCase
) : ViewModel() {

    private val _markerCode = MutableLiveData<String>()
    val markerCode: LiveData<String> = _markerCode

    private val _markerAddress = MutableLiveData<List<Address>>()
    val markerAddress: LiveData<List<Address>> = _markerAddress

    private val _currentAddress = MutableLiveData<Address?>()
    val currentAddress: LiveData<Address?> = _currentAddress

    private val _uiState = MutableStateFlow(BikeMapUiState())
    val uiState: StateFlow<BikeMapUiState> = _uiState.asStateFlow()

    fun searchNearbyPlacesMarker(code: String, longitude: String, latitude: String) {
        viewModelScope.launch {
            val result = searchNearbyPlacesForMarkerUseCase(code, longitude, latitude)
            _markerCode.value = code
            _markerAddress.postValue(result)
        }
    }

    fun getAddress() {
        _uiState.update {
            it.copy(
                isLoading = LoadingState.LOADING
            )
        }
        viewModelScope.launch {
            val address = getCurrentAddressUseCase()
            Log.d(this@BikeMapViewModel.toString(), "get current address : $address")
            if (address is Result.Success) {
                _uiState.update {
                    it.copy(
                        isLoading = LoadingState.DONE,
                        address = address.data
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = LoadingState.ERROR
                    )
                }
            }
        }
    }
}