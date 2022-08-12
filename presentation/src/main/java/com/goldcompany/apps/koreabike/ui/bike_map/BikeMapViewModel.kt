package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.usecase.GetCurrentAddressUseCase
import com.goldcompany.koreabike.domain.usecase.SearchNearbyPlacesForMarkerUseCase
import com.naver.maps.map.overlay.Marker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BikeMapViewModel @Inject constructor(
    private val searchNearbyPlacesForMarkerUseCase: SearchNearbyPlacesForMarkerUseCase,
    private val getCurrentAddressUseCase: GetCurrentAddressUseCase
) : ViewModel() {

    private val _markerCode = MutableLiveData<String>()
    val markerCode: LiveData<String> = _markerCode

    private val _markerAddress = MutableLiveData<List<Address>>()
    val markerAddress: LiveData<List<Address>> = _markerAddress

    fun searchNearbyPlacesMarker(code: String, longitude: String, latitude: String) {
        viewModelScope.launch {
            val result = searchNearbyPlacesForMarkerUseCase(code, longitude, latitude)
            _markerCode.value = code
            _markerAddress.postValue(result)
        }
    }

    suspend fun getAddress(): Flow<Address?> = flow {
        emit(getCurrentAddressUseCase())
    }
}