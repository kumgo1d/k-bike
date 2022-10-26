package com.goldcompany.apps.koreabike.ui.search_address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.apps.koreabike.util.Async
import com.goldcompany.koreabike.domain.model.Result
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.model.succeeded
import com.goldcompany.koreabike.domain.usecase.GetCurrentAddressUseCase
import com.goldcompany.koreabike.domain.usecase.InsertAddressUseCase
import com.goldcompany.koreabike.domain.usecase.SearchAddressUseCase
import com.goldcompany.koreabike.domain.usecase.UpdateCurrentAddressUnselectedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchAddressUiState(
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

    private val _isLoading = MutableStateFlow(false)
    private val _message: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _place: MutableStateFlow<String?> = MutableStateFlow(null)
    private val _page = MutableStateFlow(1)
    private val _addressAsync = _place.combine(_page) { place, page ->
        searchAddress(place, page)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Async.Loading
    )

    val uiState: StateFlow<SearchAddressUiState> = combine(
        _isLoading, _message, _addressAsync
    ) { isLoading, message, addressAsync ->
        when (addressAsync) {
            Async.Loading -> {
                SearchAddressUiState(
                    isLoading = true
                )
            }
            is Async.Success -> {
                SearchAddressUiState(
                    isLoading = false,
                    message = message,
                    items = addressAsync.data
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SearchAddressUiState(isLoading = true)
    )

    private val _addressList = MutableLiveData<List<Address>>()
    val addressList: LiveData<List<Address>> = _addressList

    fun setCurrentAddress(newAddress: Address) {
        viewModelScope.launch {
            getCurrentAddressUseCase()?.let { updateCurrentAddressUnselectedUseCase(it.id) }
            insertAddressUseCase(newAddress)
        }
    }

    fun setSearchPlace(place: String) {
        _place.value = place
        _page.value = 1
    }

    private fun searchAddress(place: String?, page: Int): Async<List<Address>> {
        if (place != null) {
            searchAddressUseCase(place, page).map { searchResult ->
                if (searchResult.succeeded && searchResult is Result.Success) {
                    return@map Async.Success(searchResult.data)
                } else {
                    return@map Async.Success(emptyList())
                }
            }
        }
        return Async.Success(emptyList())
    }
}