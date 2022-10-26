package com.goldcompany.apps.koreabike.ui.search_address

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.util.SearchAppBarState
import com.goldcompany.koreabike.domain.model.Result
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.usecase.GetCurrentAddressUseCase
import com.goldcompany.koreabike.domain.usecase.InsertAddressUseCase
import com.goldcompany.koreabike.domain.usecase.SearchAddressUseCase
import com.goldcompany.koreabike.domain.usecase.UpdateCurrentAddressUnselectedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class SearchAddressUiState(
    val page: Int = 1,
    val isLoading: Boolean = true,
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
        searchAppBarState.value = SearchAppBarState.CLOSED
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
            getCurrentAddressUseCase()?.let { updateCurrentAddressUnselectedUseCase(it.id) }
            insertAddressUseCase(newAddress)
        }
    }

    fun searchAddress() {
        _uiState.update {
            it.copy(isLoading = true)
        }
        Timber.d("Dfdfdfdf")
        searchAddressUseCase(_searchAddressState.value, _uiState.value.page).map { searchResult ->
            if (searchResult is Result.Success) {
                val addressList = searchResult.data
                _uiState.update {
                    it.copy(
                        items = addressList,
                        isLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }
}