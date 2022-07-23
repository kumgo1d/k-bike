package com.goldcompany.apps.koreabike.ui.search_address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.goldcompany.koreabike.data.model.address.ApiAddress
import com.goldcompany.koreabike.domain.model.Address
import com.goldcompany.koreabike.domain.usecase.GetCurrentAddressUseCase
import com.goldcompany.koreabike.domain.usecase.SearchAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchAddressViewModel @Inject constructor(
    private val searchAddressUseCase: SearchAddressUseCase,
    private val getCurrentAddressUseCase: GetCurrentAddressUseCase
) : ViewModel() {

    fun setCurrentAddress(userAddress: Address) {
        viewModelScope.launch {
            val current: Address? = getCurrentAddressUseCase()
            insertAddress(userAddress)
        }
    }

    private fun updateAddressUnselect(date: Long) {
        viewModelScope.launch {
            kBikeRepository.updateAddressUnselect(date)
        }
    }

    private fun insertAddress(userAddress: Address) {
        viewModelScope.launch {
            kBikeRepository.insertAddress(userAddress)
        }
    }
}