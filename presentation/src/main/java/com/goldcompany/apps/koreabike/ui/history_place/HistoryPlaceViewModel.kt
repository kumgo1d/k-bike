package com.goldcompany.apps.koreabike.ui.history_place

import androidx.lifecycle.*
import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryPlaceViewModel @Inject constructor(
    private val getCurrentAddressUseCase: GetCurrentAddressUseCase,
    private val getAllHistoryAddressUseCase: GetAllHistoryAddressUseCase,
    private val updateCurrentAddressUnselectedUseCase: UpdateCurrentAddressUnselectedUseCase,
    private val insertAddressUseCase: InsertAddressUseCase,
    private val deleteAddressUseCase: DeleteAddressUseCase
) : ViewModel() {

    private val _addressList = liveData { emit(getAllHistoryAddressUseCase()) }
    val addressList: LiveData<List<Address>> = _addressList

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