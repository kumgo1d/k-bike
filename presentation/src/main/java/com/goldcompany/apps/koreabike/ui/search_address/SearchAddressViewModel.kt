package com.goldcompany.apps.koreabike.ui.search_address

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchAddressUiState(
    val page: Int = 1,
    val isLoading: LoadingState = LoadingState.INIT,
    val message: Int? = null,
    val items: List<Address> = emptyList()
)

@HiltViewModel
class SearchAddressViewModel @Inject constructor(
    private val searchAddressUseCase: SearchAddressUseCase,
    private val getCurrentAddressUseCase: GetCurrentAddressUseCase,
    private val updateCurrentAddressUnselectedUseCase: UpdateCurrentAddressUnselectedUseCase,
    private val insertAddressUseCase: InsertAddressUseCase
) : ViewModel() {

    private val _searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(value = SearchAppBarState.CLOSED)
    val searchAppBarState = _searchAppBarState

    fun setSearchAppBarStateOpen() {
        searchAppBarState.value = SearchAppBarState.OPENED
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

    private val _searchAddressState: MutableState<String> =
        mutableStateOf(value = "")
    val searchAddressState = _searchAddressState

    fun setSearchAddressState(place: String) {
        _searchAddressState.value = place
    }

    private val _uiState = MutableStateFlow(SearchAddressUiState())
    val uiState: StateFlow<SearchAddressUiState> = _uiState.asStateFlow()

    private val _addressList = MutableLiveData<List<Address>>()
    val addressList: LiveData<List<Address>> = _addressList

    fun setCurrentAddress(newAddress: Address) {
        viewModelScope.launch {
            val address = getCurrentAddressUseCase()
            if (address is Result.Success) {
                address.data?.let { updateCurrentAddressUnselectedUseCase(it.id) }
            }
            insertAddressUseCase(newAddress)
        }
    }

    fun searchAddress(place: String) {
        _uiState.update {
            it.copy(isLoading = LoadingState.LOADING)
        }
        viewModelScope.launch {
            val searchResult = searchAddressUseCase(place, _uiState.value.page)
            if (searchResult is Result.Success) {
                val addressList = searchResult.data
                _uiState.update {
                    it.copy(
                        items = addressList,
                        isLoading = LoadingState.DONE
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