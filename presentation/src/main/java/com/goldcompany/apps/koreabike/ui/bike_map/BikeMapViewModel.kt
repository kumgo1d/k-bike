package com.goldcompany.apps.koreabike.ui.bike_map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.util.Async
import com.goldcompany.apps.koreabike.util.LoadingState
import com.goldcompany.koreabike.domain.model.Result
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.usecase.GetCurrentAddressUseCase
import com.goldcompany.koreabike.domain.usecase.SearchNearbyPlacesForMarkerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    fun searchNearbyPlacesMarker(code: String, longitude: String, latitude: String) {
        viewModelScope.launch {
            val result = searchNearbyPlacesForMarkerUseCase(code, longitude, latitude)
            _markerCode.value = code
            _markerAddress.postValue(result)
        }
    }

    private val _message: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _addressAsync = getCurrentAddressUseCase().map {
        getCurrentAddress(it)
    }.map { Async.Success(it) }
    .onStart<Async<Address?>> { emit(Async.Loading) }

    val uiState: StateFlow<BikeMapUiState> = combine(
        _message, _addressAsync
    ) { msessage, addressAsync ->
        when (addressAsync) {
            Async.Loading -> {
                BikeMapUiState(isLoading = LoadingState.LOADING)
            }
            is Async.Success -> {
                BikeMapUiState(
                    isLoading = LoadingState.DONE,
                    address = addressAsync.data
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = BikeMapUiState(isLoading = LoadingState.INIT)
    )

    private fun getCurrentAddress(address: Result<Address?>): Address? {
        return if (address is Result.Success) {
            address.data
        } else {
            null
        }
    }
}