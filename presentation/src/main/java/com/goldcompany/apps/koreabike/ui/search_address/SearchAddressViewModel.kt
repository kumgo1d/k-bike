package com.goldcompany.apps.koreabike.ui.search_address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.usecase.GetCurrentAddressUseCase
import com.goldcompany.koreabike.domain.usecase.InsertAddressUseCase
import com.goldcompany.koreabike.domain.usecase.SearchAddressUseCase
import com.goldcompany.koreabike.domain.usecase.UpdateCurrentAddressUnselectedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchAddressViewModel @Inject constructor(
    private val searchAddressUseCase: SearchAddressUseCase,
    private val getCurrentAddressUseCase: GetCurrentAddressUseCase,
    private val updateCurrentAddressUnselectedUseCase: UpdateCurrentAddressUnselectedUseCase,
    private val insertAddressUseCase: InsertAddressUseCase
) : ViewModel() {

    fun setCurrentAddress(newAddress: Address) {
        viewModelScope.launch {
            getCurrentAddressUseCase()?.let { updateCurrentAddressUnselectedUseCase(it.id) }
            insertAddress(newAddress)
        }
    }

    private fun insertAddress(address: Address) {
        viewModelScope.launch {
            insertAddressUseCase(address)
        }
    }
}