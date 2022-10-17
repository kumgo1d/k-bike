package com.goldcompany.apps.koreabike.ui.history_place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goldcompany.koreabike.domain.model.address.Address
import com.goldcompany.koreabike.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HistoryPlacesUiState {
    data class Success(val items: List<Address>): HistoryPlacesUiState()
    data class Exception(val e: Throwable): HistoryPlacesUiState()
}

@HiltViewModel
class HistoryPlaceViewModel @Inject constructor(
    private val getCurrentAddressUseCase: GetCurrentAddressUseCase,
    private val getAllHistoryAddressUseCase: GetAllHistoryAddressUseCase,
    private val updateCurrentAddressUnselectedUseCase: UpdateCurrentAddressUnselectedUseCase,
    private val insertAddressUseCase: InsertAddressUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)

    private val _uiState = MutableStateFlow(HistoryPlacesUiState.Success(emptyList()))
    val uiState: StateFlow<HistoryPlacesUiState> = _uiState

    init {
        viewModelScope.launch {
            getAllHistoryAddressUseCase.invoke().collectLatest {
                _uiState.value = HistoryPlacesUiState.Success(it)
            }
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