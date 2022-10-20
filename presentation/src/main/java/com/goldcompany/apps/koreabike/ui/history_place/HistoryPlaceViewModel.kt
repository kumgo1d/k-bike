package com.goldcompany.apps.koreabike.ui.history_place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.model.Result
import com.goldcompany.koreabike.domain.model.succeeded
import com.goldcompany.koreabike.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HistoryPlacesUiState {
    data class Success(val items: List<Address>): HistoryPlacesUiState()
    data class Exception(val e: Throwable): HistoryPlacesUiState()
}

sealed class Async<out T> {
    object Loading : Async<Nothing>()
    data class Success<out T>(val data: T) : Async<T>()
}

data class PlaceUiState(
    val isLoading: Boolean = true,
    val items: List<Address> = emptyList()
)

@HiltViewModel
class HistoryPlaceViewModel @Inject constructor(
    private val getCurrentAddressUseCase: GetCurrentAddressUseCase,
    private val getAllHistoryAddressUseCase: GetAllHistoryAddressUseCase,
    private val updateCurrentAddressUnselectedUseCase: UpdateCurrentAddressUnselectedUseCase,
    private val insertAddressUseCase: InsertAddressUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _itemsAsync =
        combine(getAllHistoryAddressUseCase.invoke()) {
            getAddressList(it.component1())
        }.map { Async.Success(it) }
        .onStart<Async<List<Address>>> { emit(Async.Loading) }

    val uiState: StateFlow<PlaceUiState> = combine(
        _isLoading, _itemsAsync
    ) { isLoading, itemsAsync ->
        when (itemsAsync) {
            Async.Loading -> {
                PlaceUiState(isLoading = true)
            }
            is Async.Success -> {
                PlaceUiState(
                    isLoading = false,
                    items = itemsAsync.data
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = PlaceUiState(isLoading = true)
    )

    private fun getAddressList(address: Result<List<Address>>): List<Address> {
        return if (address.succeeded && address is Result.Success) {
            address.data
        } else {
            emptyList()
        }
    }

    fun setCurrentAddress(address: Address) {
        viewModelScope.launch {
            getCurrentAddressUseCase()?.let { updateCurrentAddressUnselectedUseCase(it.id) }
            insertAddressUseCase(address)
        }
    }

    fun deleteAddress(address: Address) {
        viewModelScope.launch {
            deleteAddressUseCase(address)
        }
    }
}