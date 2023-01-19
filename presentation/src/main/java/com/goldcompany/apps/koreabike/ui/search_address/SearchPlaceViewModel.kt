package com.goldcompany.apps.koreabike.ui.search_address

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.util.LoadingState
import com.goldcompany.apps.koreabike.util.SearchAppBarState
import com.goldcompany.koreabike.domain.model.Result
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.usecase.GetCurrentAddressUseCase
import com.goldcompany.koreabike.domain.usecase.InsertAddressUseCase
import com.goldcompany.koreabike.domain.usecase.SearchAddressUseCase
import com.goldcompany.koreabike.domain.usecase.UpdateCurrentAddressUnselectedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class SearchAddressUiState(
    val isLoading: LoadingState = LoadingState.INIT,
    val message: Int? = null,
    val items: List<Address> = emptyList(),
    val page: Int = 1,
    val currentPlace: String = ""
)

@HiltViewModel
class SearchAddressViewModel @Inject constructor(
    private val searchAddressUseCase: SearchAddressUseCase,
    private val getCurrentAddressUseCase: GetCurrentAddressUseCase,
    private val updateCurrentAddressUnselectedUseCase: UpdateCurrentAddressUnselectedUseCase,
    private val insertAddressUseCase: InsertAddressUseCase
) : ViewModel() {

    private val _currentAddress = MutableStateFlow<Address?>(null)

    init {
        viewModelScope.launch {
            getCurrentAddressUseCase().collectLatest {
                if (it is Result.Success) {
                    _currentAddress.value = it.data
                }
            }
        }
    }

    private val _searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(value = SearchAppBarState.CLOSED)
    val searchAppBarState = _searchAppBarState

    fun setSearchAppBarStateOpen() {
        searchAppBarState.value = SearchAppBarState.OPENED
    }

    private val _searchAddressState: MutableState<String> =
        mutableStateOf(value = "")
    val searchAddressState = _searchAddressState

    fun setSearchAddressState(place: String) {
        _searchAddressState.value = place
    }

    fun setSearchAppBarStateClose() {
        if (_searchAddressState.value.isEmpty()) {
            searchAppBarState.value = SearchAppBarState.CLOSED
            _uiState.update {
                it.copy(
                    page = 1,
                    isLoading = LoadingState.INIT,
                    items = emptyList()
                )
            }
        } else {
            _searchAddressState.value = ""
        }
    }

    private val _uiState = MutableStateFlow(SearchAddressUiState())
    val uiState: StateFlow<SearchAddressUiState> = _uiState.asStateFlow()

    fun setCurrentAddress(newAddress: Address) {
        viewModelScope.launch {
            _currentAddress.value?.let { updateCurrentAddressUnselectedUseCase(it.id) }
            insertAddressUseCase(newAddress)
        }
    }

    fun searchAddress(place: String? = null) {
        if (place != null && _uiState.value.currentPlace != place) {
            _uiState.update {
                it.copy(
                    isLoading = LoadingState.LOADING,
                    page = 1
                )
            }
        } else {
            _uiState.update {
                it.copy(isLoading = LoadingState.LOADING)
            }
        }



        viewModelScope.launch {
            val currentPlace = place ?: _uiState.value.currentPlace
            val searchResult = searchAddressUseCase(
                address = currentPlace,
                page = _uiState.value.page
            )

            delay(500)

            if (searchResult is Result.Success) {
                val addressList = searchResult.data
                _uiState.update {
                    it.copy(
                        items = it.items + addressList,
                        isLoading = LoadingState.DONE,
                        page = it.page + 1,
                        currentPlace = currentPlace
                    )
                }
            } else {
                _uiState.update {
                    it.copy(isLoading = LoadingState.ERROR)
                }
            }
        }
    }
}